package br.com.sosysters.controllers;

import br.com.sosysters.services.UsuariaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private UsuariaService usuariaService;

    public LoginController(UsuariaService usuariaService) {
        this.usuariaService = usuariaService;
    }

    @GetMapping("/login")
    public String loadListingPage() {
        return "auth/login";
    }

    @GetMapping("/auth/login")
    public String redirectAuthLogin(@RequestParam(name = "continue", required = false) String continueParam) {
        // Redireciona /auth/login para a rota correta /login para evitar loops quando links antigos
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String loadLogoutPage() {
        return "auth/logout";
    }

    @GetMapping("/verify-email/{uuid}")
    public String loadVerifyEmailPage(@PathVariable("uuid") String uuid, Model model) {
        String resultado = usuariaService.verificaToken(uuid);
        model.addAttribute("message", resultado);
        // Retorna uma página que mostra a mensagem e redireciona o usuário para o login
        return "auth/verify-result";
    }

    @GetMapping("/access-denied")
    public String loadAccessDeniedPage() {
        return "auth/access-denied";
    }

  @PostMapping("/resend-confirmation")
  public String reenviarConfirmacao(@RequestParam("email") String email,
                RedirectAttributes redirectAttributes) {
    String mensagem = usuariaService.reenviarEmailConfirmacao(email);
    redirectAttributes.addFlashAttribute("message", mensagem);
    return "redirect:/login";
  }
}