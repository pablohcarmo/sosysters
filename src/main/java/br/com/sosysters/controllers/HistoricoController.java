package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/historico")
public class HistoricoController {

    @GetMapping
    public String loadHistoricoPage() {
        return "historico";
    }
}

