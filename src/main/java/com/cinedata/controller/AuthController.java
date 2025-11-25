package com.cinedata.controller;

import com.cinedata.model.Dados;
import com.cinedata.model.Usuario;
import com.cinedata.repository.DadosRepository;
import com.cinedata.repository.UsuarioRepository;
import com.cinedata.util.Criptografia;
import com.cinedata.util.Validacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private DadosRepository dadosRepository;

    @PostMapping("/login")
    public String login(@RequestParam String email,
                       @RequestParam String senha,
                       RedirectAttributes redirectAttributes) {
        try {
            // Buscar usuário pelo email dos dados
            Usuario usuario = usuarioRepository.findByDadosEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

            // Verificar senha (MD5)
            String senhaCriptografada = Criptografia.criptografarMD5(senha);
            if (!usuario.getSenha().equals(senhaCriptografada)) {
                redirectAttributes.addFlashAttribute("erro", "Senha incorreta");
                return "redirect:/login";
            }

            // Verificar se usuário está ativo
            if (!usuario.isAtivo()) {
                redirectAttributes.addFlashAttribute("erro", "Usuário inativo");
                return "redirect:/login";
            }

            redirectAttributes.addFlashAttribute("mensagem", "Login realizado com sucesso!");
            redirectAttributes.addFlashAttribute("usuario", usuario.getDados().getNome());
            return "redirect:/";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro no login: " + e.getMessage());
            return "redirect:/login";
        }
    }

    @PostMapping("/cadastro")
    public String cadastrar(@RequestParam String nome,
                           @RequestParam String email,
                           @RequestParam String senha,
                           @RequestParam String confirmarSenha,
                           RedirectAttributes redirectAttributes) {
        try {
            // Validações
            if (!Validacao.validarEmail(email)) {
                redirectAttributes.addFlashAttribute("erro", "Email inválido");
                return "redirect:/cadastro";
            }

            if (!Validacao.validarSenha(senha)) {
                redirectAttributes.addFlashAttribute("erro", "Senha deve ter pelo menos 6 caracteres");
                return "redirect:/cadastro";
            }

            if (!senha.equals(confirmarSenha)) {
                redirectAttributes.addFlashAttribute("erro", "Senhas não coincidem");
                return "redirect:/cadastro";
            }

            // Verificar se email já existe
            if (usuarioRepository.findByDadosEmail(email).isPresent()) {
                redirectAttributes.addFlashAttribute("erro", "Email já cadastrado");
                return "redirect:/cadastro";
            }

            // Criar dados
            Dados dados = new Dados();
            dados.setNome(nome);
            dados.setEmail(email);
            dados.setCpf("000.000.000-00"); // Em produção seria coletado
            dados.setNascimento(LocalDate.now().minusYears(18)); // Default 18 anos

            dadosRepository.save(dados);

            // Criar usuário
            Usuario usuario = new Usuario();
            usuario.setDados(dados);
            usuario.setNickname(nome.split(" ")[0]); // Primeiro nome como nickname
            usuario.setSenha(Criptografia.criptografarMD5(senha));
            usuario.setAtivo(true);
            usuario.setGerente(false);

            usuarioRepository.save(usuario);

            redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso! Faça login para continuar.");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro no cadastro: " + e.getMessage());
            return "redirect:/cadastro";
        }
    }
}