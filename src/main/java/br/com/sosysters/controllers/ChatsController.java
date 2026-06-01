package br.com.sosysters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chats")
public class ChatsController {

    @GetMapping
    public String loadChatsPage() {
        return "em-construcao";
    }
}