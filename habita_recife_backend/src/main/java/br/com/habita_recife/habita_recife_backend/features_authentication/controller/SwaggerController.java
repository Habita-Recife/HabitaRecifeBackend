package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class SwaggerController {

    @GetMapping
    @Operation(summary = "Lista todos os usuários", description = "Retorna uma lista de usuários cadastrados no sistema.")
    public String listarUsuarios() {
        return "Lista de usuários";
    }
}
