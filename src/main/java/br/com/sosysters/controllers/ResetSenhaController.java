package br.com.sosysters.controllers;

import br.com.sosysters.services.UsuariaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ResetSenhaController {
    private UsuariaService usuariaService;

    public ResetSenhaController(UsuariaService usuariaService) {
        this.usuariaService = usuariaService;
    }

    @GetMapping("/reset-senha")
    public String showResetSenhaForm() {
        return "auth/reset-senha";
    }

    @PostMapping("/reset-senha")
    public String processResetSenha(@RequestParam("email") String email,
                                    RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {
        String mensagem = usuariaService.enviarLinkResetSenha(email, request);
        redirectAttributes.addFlashAttribute("message", mensagem);
        return "redirect:/reset-senha";
    }

    @GetMapping("/reset-senha/{uuid}")
    public String showResetSenhaFormComToken(@PathVariable("uuid") String uuid, Model model) {
        // Valida o token e retorna o formulário para inserir nova senha
        String resultado = usuariaService.validarTokenResetSenha(uuid);
        if (resultado.contains("inválido") || resultado.contains("expirado")) {
            model.addAttribute("error", resultado);
            return "auth/reset-senha";
        }
        model.addAttribute("token", uuid);
        return "auth/reset-senha-form";
    }

    @PostMapping("/reset-senha/confirmar")
    public String confirmarNovaSenh(@RequestParam("token") String token,
                                     @RequestParam("senha") String senha,
                                     @RequestParam("confirmaSenha") String confirmaSenha,
                                     RedirectAttributes redirectAttributes) {
        if (!senha.equals(confirmaSenha)) {
            redirectAttributes.addFlashAttribute("error", "As senhas não conferem!");
            return "redirect:/reset-senha/" + token;
        }

        String resultado = usuariaService.resetarSenha(token, senha);
        if (resultado.contains("sucesso")) {
            redirectAttributes.addFlashAttribute("message", "Senha atualizada com sucesso! Faça login com sua nova senha.");
            return "redirect:/login";
        } else {
            redirectAttributes.addFlashAttribute("error", resultado);
            return "redirect:/reset-senha/" + token;
        }
    }
}

