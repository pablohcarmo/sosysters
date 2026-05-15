package br.com.sosysters.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public String enviarEmail(String destinatario, String assunto, String mensagem) {
        try {
            SimpleMailMessage simpleMailmessage = new SimpleMailMessage();
            simpleMailmessage.setFrom(remetente);
            simpleMailmessage.setTo(destinatario);
            simpleMailmessage.setSubject(assunto);
            simpleMailmessage.setText(mensagem);
            javaMailSender.send(simpleMailmessage);
            return "Email enviado com sucesso!";
        } catch(Exception e) {
            return "Erro ao enviar email: " + e.getLocalizedMessage();
        }
    }
}