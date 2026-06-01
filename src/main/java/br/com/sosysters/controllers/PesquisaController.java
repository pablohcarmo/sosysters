package br.com.sosysters.controllers;

import br.com.sosysters.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pesquisa")
public class PesquisaController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public String loadPesquisaPage(Model model) {
        model.addAttribute("solicitacoesPesquisa", solicitacaoService.listarParaPesquisa());
        model.addAttribute("categoriasPesquisa", solicitacaoService.listarCategoriasPesquisa());
        return "pesquisa";
    }
}

