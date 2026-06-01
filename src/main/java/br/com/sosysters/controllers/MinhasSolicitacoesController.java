package br.com.sosysters.controllers;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/minhas-solicitacoes")
public class MinhasSolicitacoesController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @GetMapping
    public String loadMinhasSolicitacoesPage(@AuthenticationPrincipal Usuaria usuaria, Model model) {
        model.addAttribute("solicitacoes", solicitacaoService.listarPorUsuario(usuaria.getUsername()));
        return "minhas-solicitacoes";
    }
}

