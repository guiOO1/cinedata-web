package com.cinedata.controller;

import com.cinedata.model.Sessao;
import com.cinedata.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sessoes")
public class SessaoController {

    @Autowired
    private SessaoRepository sessaoRepository;

    @GetMapping
    public String listarSessoes(Model model) {
        List<Sessao> sessoes = sessaoRepository.findByAtivaTrueAndDataHoraAfter(LocalDateTime.now());
        model.addAttribute("sessoes", sessoes);
        return "sessoes";
    }

    @GetMapping("/{id}")
    public String detalhesSessao(@PathVariable Long id, Model model) {
        Sessao sessao = sessaoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Sessão não encontrada"));
        model.addAttribute("sessoes", sessaoRepository.findByAtivaTrueAndDataHoraAfter(LocalDateTime.now()));
        model.addAttribute("sessao", sessao);
        return "reservas";
    }
}