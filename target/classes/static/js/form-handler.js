package com.cinedata.controller;

import com.cinedata.model.Filme;
import com.cinedata.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class HomeController {
    
    @Autowired
    private FilmeRepository filmeRepository;
    
    @GetMapping("/")
    public String home(Model model) {
        List<Filme> filmesEmCartaz = filmeRepository.findByEmCartazTrue();
        model.addAttribute("filmes", filmesEmCartaz);
        return "index";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/cadastro") 
    public String cadastro() {
        return "cadastro";
    }
    
    @GetMapping("/confirmacao")
    public String confirmacao() {
        return "confirmacao";
    }
}