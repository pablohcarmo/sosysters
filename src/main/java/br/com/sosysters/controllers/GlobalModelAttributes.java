package br.com.sosysters.controllers;

import java.util.Base64;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.UsuariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalModelAttributes {

    @Autowired
    private UsuariaService usuariaService;

    @ModelAttribute("fotoPerfilBase64")
    public String carregarFotoPerfil() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof Usuaria usuaria)) {
            return null;
        }

        if (usuaria.getUsername() == null || usuaria.getUsername().isBlank()) {
            return null;
        }

        return usuariaService.carregarFotoPerfil(usuaria.getUsername())
                .map(foto -> Base64.getEncoder().encodeToString(foto.getImgPerfil()))
                .orElse(null);
    }
}


