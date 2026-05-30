package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/carteira")
public class CarteiraController {

    @GetMapping
    public String loadCarteiraPage() {
        return "carteira";
    }
}

