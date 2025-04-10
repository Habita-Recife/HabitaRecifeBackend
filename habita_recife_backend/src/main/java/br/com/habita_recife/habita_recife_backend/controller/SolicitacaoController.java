package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.SolicitacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsMorador;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsSindico;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/solicitacao")
@Tag(name = "Solicitação", description = "Visualização das solicitações")
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar as solicitações feitas.")
    public ResponseEntity<List<Solicitacao>> listarTodos() {
        List<Solicitacao> solicitacao = solicitacaoService.listarTodos();
        return ResponseEntity.ok(solicitacao);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Obter detalhes de uma solicitação específica.")
    public ResponseEntity<Solicitacao> buscarPorId(@PathVariable long id) {
        Optional<Solicitacao> solicitacao = solicitacaoService.buscarPorId(id);
        return solicitacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @IsMorador
    @Operation(summary = "Criar", description = "Criar uma nova solicitação.")
    public ResponseEntity<Solicitacao> salvar(@RequestBody SolicitacaoDTO solicitacaoDTO) {
        Solicitacao novaSolicitacao = solicitacaoService.salvar(solicitacaoDTO);
        return ResponseEntity.ok(novaSolicitacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar uma solicitação existente.")
    public ResponseEntity<Solicitacao> atualizar(@PathVariable long id, @RequestBody SolicitacaoDTO solicitacaoDTO) {
        Solicitacao solicitacaoAtualizada = solicitacaoService.atualizar(id, solicitacaoDTO);
        return ResponseEntity.ok(solicitacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir uma solicitação existente.")
    public ResponseEntity<Void> excluir(@PathVariable long id) {
        solicitacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/aprovar")
    @IsSindico
    @Operation(summary = "Aprovar", description = "Aprovar uma solicitação existente.")
    public ResponseEntity<Solicitacao> aprovarSolicitacao(@PathVariable long id) {
        Solicitacao solicitacaoAprovada = solicitacaoService.aprovar(id);
        return ResponseEntity.ok(solicitacaoAprovada);
    }

    @PutMapping("/{id}/recusar")
    @IsSindico
    @Operation(summary = "Recusar", description = "Recusar uma solicitação existente.")
    public ResponseEntity<Solicitacao> recusarSolicitacao(@PathVariable long id) {
        Solicitacao solicitacaoRecusada = solicitacaoService.recusar(id);
        return ResponseEntity.ok(solicitacaoRecusada);
    }
}