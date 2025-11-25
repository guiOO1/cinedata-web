package com.cinedata.controller;

import com.cinedata.model.*;
import com.cinedata.repository.*;
import com.cinedata.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private SessaoRepository sessaoRepository;

    @Autowired
    private AssentoRepository assentoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaService reservaService;

    @PostMapping("/nova")
    public String criarReserva(@RequestParam Long sessaoId,
                             @RequestParam String assentos,
                             @RequestParam int quantidade,
                             RedirectAttributes redirectAttributes) {
        try {
            Sessao sessao = sessaoRepository.findById(sessaoId)
                .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));

            // Simular usuário (em produção viria da sessão/login)
            Usuario usuario = usuarioRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

            // Verificar se pode reservar
            if (!reservaService.podeReservar(usuario, sessao)) {
                redirectAttributes.addFlashAttribute("erro", "Não é possível realizar a reserva");
                return "redirect:/sessoes";
            }

            // Processar assentos
            String[] assentosArray = assentos.split(",");
            
            // Para cada assento selecionado, criar uma reserva
            for (String assentoIdentificador : assentosArray) {
                if (!assentoIdentificador.trim().isEmpty()) {
                    // Criar assento temporário (em produção buscaria do banco)
                    Assento assento = new Assento();
                    assento.setColuna(assentoIdentificador.substring(0, 1));
                    assento.setNumero(Integer.parseInt(assentoIdentificador.substring(1)));
                    
                    // Verificar se assento já está reservado
                    boolean assentoOcupado = reservaRepository.existsBySessaoIdAndValidaTrue(sessaoId);
                    if (assentoOcupado) {
                        redirectAttributes.addFlashAttribute("erro", "Alguns assentos já estão ocupados");
                        return "redirect:/sessoes/" + sessaoId;
                    }

                    // Criar reserva
                    Reserva reserva = new Reserva();
                    reserva.setAssento(assento);
                    reserva.setSessao(sessao);
                    reserva.setUsuario(usuario);
                    reserva.setHoraReserva(LocalDateTime.now());
                    reserva.setValida(true);

                    reservaRepository.save(reserva);
                }
            }

            // Calcular total
            double total = sessao.getPreco() * quantidade;
            
            redirectAttributes.addFlashAttribute("reservaCodigo", "BRT" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            redirectAttributes.addFlashAttribute("filme", sessao.getFilme().getNome());
            redirectAttributes.addFlashAttribute("data", sessao.getDataHora());
            redirectAttributes.addFlashAttribute("assentos", assentos);
            redirectAttributes.addFlashAttribute("total", total);
            redirectAttributes.addFlashAttribute("mensagem", "Reserva realizada com sucesso!");

            return "redirect:/confirmacao";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao realizar reserva: " + e.getMessage());
            return "redirect:/sessoes";
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public String listarReservasUsuario(@PathVariable Long usuarioId, Model model) {
        List<Reserva> reservas = reservaRepository.findByUsuarioIdAndValidaTrue(usuarioId);
        model.addAttribute("reservas", reservas);
        return "minhas-reservas";
    }

    @PostMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada"));
        
        reserva.setValida(false);
        reservaRepository.save(reserva);

        redirectAttributes.addFlashAttribute("mensagem", "Reserva cancelada com sucesso!");
        return "redirect:/reservas/usuario/" + reserva.getUsuario().getId();
    }
}