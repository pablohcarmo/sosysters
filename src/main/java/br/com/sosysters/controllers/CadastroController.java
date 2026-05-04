package br.com.sosysters.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sosysters.services.EtniaService;
import br.com.sosysters.services.GeneroService;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private EtniaService etniaService;

    @GetMapping
    public String loadCadastroPage(Model model) {
        model.addAttribute("generos", generoService.listGeneros());
        model.addAttribute("etnias", etniaService.listEtnias());
        return "cadastro";
    }
}
