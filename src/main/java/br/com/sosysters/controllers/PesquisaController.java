package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pesquisa")
public class PesquisaController {

    @GetMapping
    public String loadPesquisaPage() {
        return "pesquisa";
    }
}

