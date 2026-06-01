package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @GetMapping
    public String loadAvaliacoesPage() {
        return "avaliacoes";
    }
}

