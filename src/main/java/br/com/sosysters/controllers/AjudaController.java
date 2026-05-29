package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/ajuda"})
public class AjudaController {
    @GetMapping
    public String loadIndexPage() {
        return "ajuda";
    }
}
