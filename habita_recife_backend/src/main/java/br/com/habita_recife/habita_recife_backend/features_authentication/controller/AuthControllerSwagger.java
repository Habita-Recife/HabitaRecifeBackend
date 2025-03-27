package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
public class AuthControllerSwagger {

    @RestController
    @RequestMapping("/api/auth")
    @Tag(name = "Autenticação", description = "Endpoints de login e autenticação")
    public class AuthController {

        @PostMapping("/login")
        @Operation(summary = "Autenticação de usuário", description = "Realiza login e retorna um token de acesso.")
        public String login() {
            return "Token JWT";
        }
    }

}
