package br.com.habita_recife.habita_recife_backend.features_authentication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class CondominioSwaggerController {

    @RestController
    @RequestMapping("/api/condominios")
    @Tag(name = "Condomínios", description = "Gestão de condomínios")
    public class CondominioController {

        @GetMapping
        @Operation(summary = "Lista todos os condomínios", description = "Retorna uma lista de condomínios cadastrados no sistema.")
        public String listarCondominios() {
            return "Lista de condomínios";
        }
    }

}
