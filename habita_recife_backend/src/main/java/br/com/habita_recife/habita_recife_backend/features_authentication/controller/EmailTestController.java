package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import br.com.habita_recife.habita_recife_backend.features_authentication.service.impl.EmailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class EmailTestController {

    private final EmailServiceImpl emailService;

    public EmailTestController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendTestEmail() {
        String to = "viniciuscontato2024@gmail.com";
        String subject = "Teste de E-mail Spring Boot";
        String body = "Este é um teste de envio de e-mail no Spring Boot.";

        String response = emailService.sendEmail(to, subject, body);
        return ResponseEntity.ok(response);
    }
}
