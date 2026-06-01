package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import br.com.sosysters.entities.Usuaria;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/suporte"})
public class SuporteController {
    @GetMapping
    public String loadIndexPage() {
        return "suporte";
    }

    @PostMapping
    public String submitSupportRequest(
            @AuthenticationPrincipal Usuaria usuaria,
            @RequestParam("nome") String nome,
            @RequestParam("descricao") String descricao,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            RedirectAttributes redirectAttributes) {
        // TODO: Salvar a solicitação de suporte no banco de dados
        // Por enquanto, apenas confirmar o recebimento
        redirectAttributes.addFlashAttribute("message", "Solicitação de suporte recebida com sucesso! Entraremos em contato em breve.");
        return "redirect:/suporte";
    }
}
