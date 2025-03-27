package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/solicitacao")
@Tag(name = "Solicitação", description = "Visualização das solicitaçoes")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }


    @GetMapping
    @Operation(summary = "Listar", description = "Listar as solicitaçoes feitas.")
    public ResponseEntity<List<Solicitacao>> listarTodos() {
        List<Solicitacao> solicitacao = solicitacaoService.listarTodos();
        return ResponseEntity.ok(solicitacao);
    }
}
