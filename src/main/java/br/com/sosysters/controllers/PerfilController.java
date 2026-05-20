package br.com.sosysters.controllers;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.UsuariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuariaService usuariaService;
    
    @GetMapping
    public String loadPerfilPage(@AuthenticationPrincipal Usuaria usuaria, Model model) {
        model.addAttribute("cadastro", usuariaService.carregarMeuCadastro(usuaria.getUsername()));
        return "perfil";
    }
}