package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/solicitacao")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @Autowired
    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Solicitacao>> listarTodos() {
        List<Solicitacao> solicitacao = solicitacaoService.listarTodos();
        return ResponseEntity.ok(solicitacao);
    }
}
