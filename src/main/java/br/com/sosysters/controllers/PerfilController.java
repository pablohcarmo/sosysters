package br.com.sosysters.controllers;

import java.util.Base64;

import br.com.sosysters.entities.Usuaria;
import br.com.sosysters.services.UsuariaService;
import br.com.sosysters.services.ProfissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private UsuariaService usuariaService;

    @Autowired
    private ProfissaoService profissaoService;

    @GetMapping
    public String loadPerfilPage(@AuthenticationPrincipal Usuaria usuaria, Model model) {
        model.addAttribute("cadastro", usuariaService.carregarMeuCadastro(usuaria.getUsername()));
        usuariaService.carregarFotoPerfil(usuaria.getUsername())
                .filter(foto -> foto.getImgPerfil() != null)
                .ifPresent(foto -> model.addAttribute("fotoPerfilBase64",
                        "data:image/*;base64," + Base64.getEncoder().encodeToString(foto.getImgPerfil())));

        // Carregar status de verificação de identidade com tratamento de erro
        try {
            boolean identidadeVerificada = usuariaService.verificarIdentidadeVerificada(usuaria.getUsername());
            model.addAttribute("identidadeVerificada", identidadeVerificada);
        } catch (Exception e) {
            model.addAttribute("identidadeVerificada", false);
        }

        // Carregar profissão da prestadora
        try {
            var profissao = profissaoService.carregarProfissao(usuaria.getUsername());
            model.addAttribute("profissaoAtual", profissao != null ? profissao.getNomeProfissao() : "Definir profissão");
        } catch (Exception e) {
            model.addAttribute("profissaoAtual", "Definir profissão");
        }

        return "perfil";
    }

    @PostMapping("/foto")
    public String atualizarFotoPerfil(@AuthenticationPrincipal Usuaria usuaria,
                                      @RequestParam("fotoPerfil") MultipartFile fotoPerfil,
                                      RedirectAttributes redirectAttributes) {
        if (fotoPerfil == null || fotoPerfil.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagem", "Selecione uma imagem para enviar.");
            return "redirect:/perfil";
        }

        if (fotoPerfil.getSize() > 5 * 1024 * 1024) {
            redirectAttributes.addFlashAttribute("mensagem", "A imagem deve ter no máximo 5MB.");
            return "redirect:/perfil";
        }

        try {
            usuariaService.salvarFotoPerfil(usuaria.getUsername(), fotoPerfil.getInputStream().readAllBytes());
            redirectAttributes.addFlashAttribute("mensagem", "Foto de perfil atualizada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Não foi possível atualizar a foto de perfil.");
        }

        return "redirect:/perfil";
    }

    @PostMapping("/verificar-identidade")
    public String verificarIdentidade(@AuthenticationPrincipal Usuaria usuaria,
                                      @RequestParam("selfie") MultipartFile selfie,
                                      @RequestParam("documento") MultipartFile documento,
                                      RedirectAttributes redirectAttributes) {
        if (selfie == null || selfie.isEmpty() || documento == null || documento.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagem", "Por favor, envie ambas as imagens (selfie e documento).");
            return "redirect:/perfil";
        }

        if (selfie.getSize() > 10 * 1024 * 1024 || documento.getSize() > 10 * 1024 * 1024) {
            redirectAttributes.addFlashAttribute("mensagem", "Cada imagem deve ter no máximo 10MB.");
            return "redirect:/perfil";
        }

        try {
            usuariaService.salvarFotosVerificacao(usuaria.getUsername(), selfie.getInputStream().readAllBytes(), documento.getInputStream().readAllBytes());
            redirectAttributes.addFlashAttribute("mensagem", "Dados enviados, em até 3 dias úteis, você receberá um retorno sobre a verificação por e-mail!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Não foi possível verificar a identidade. Tente novamente.");
        }

        return "redirect:/perfil";
    }
}