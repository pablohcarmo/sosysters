package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.service.annotation.GetExchange;

@Controller
public class LoginController {
    @GetMapping ("/login")
    public String loadLoginPage(){
        return "resources/static/index.html";
    }
}