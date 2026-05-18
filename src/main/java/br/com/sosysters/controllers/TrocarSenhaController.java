package br.com.sosysters.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/trocar-senha")
public class TrocarSenhaController {

    @GetMapping
    public String loadTrocarSenha() {
        return "trocar-senha";
    }
}