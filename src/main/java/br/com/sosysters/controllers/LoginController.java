package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loadListingPage() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String loadLogoutPage() {
        return "auth/logout";
    }
}