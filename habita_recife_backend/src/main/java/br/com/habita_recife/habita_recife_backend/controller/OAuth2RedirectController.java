package br.com.habita_recife.habita_recife_backend.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@Tag(name = "Autenticação OAuth2", description = "Endpoints para o fluxo de autenticação OAuth2")
public class OAuth2RedirectController {


    @GetMapping("/oauth2/redirect")
    @Operation(summary = "Recebe e retorna o token de redirecionamento OAuth2", description = "Endpoint para receber o JWT após o login bem-sucedido com o OAuth2 e retorná-lo como JSON.")
    @ResponseBody // Indica que o retorno será no corpo da resposta
    public ResponseEntity<Map<String, String>> oauth2Redirect(@RequestParam("token") String token) {
        // Log the token for debugging purposes (remove in production)
        System.out.println("Token recebido via OAuth2 Redirect: " + token);


        return ResponseEntity.ok(Map.of("token", token));
    }
}
