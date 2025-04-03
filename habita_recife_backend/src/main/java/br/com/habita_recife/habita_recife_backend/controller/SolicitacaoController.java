package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/solicitacao")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Solicitacao>> listarTodos() {
        List<Solicitacao> solicitacao = solicitacaoService.listarTodos();
        return ResponseEntity.ok(solicitacao);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Solicitacao> buscarPorTitulo(@PathVariable String titulo) {
        Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorTitulo(titulo);
        return solicitacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
