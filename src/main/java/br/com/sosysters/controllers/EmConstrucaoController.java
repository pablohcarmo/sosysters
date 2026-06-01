package br.com.sosysters.controllers;

import br.com.sosysters.entities.Usuaria;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/em-construcao")
public class EmConstrucaoController {

    @GetMapping
    public String loadConstrucaoPage() {
        return "em-construcao";
    }
}

