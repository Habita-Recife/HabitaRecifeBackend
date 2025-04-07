package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.NotificacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Notificacao;
import br.com.habita_recife.habita_recife_backend.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/notificacoes")
@Tag(name = "Notificação", description = "Gerenciamento da notificação")
public class NotificacaoController {

    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todas as notificações.")
    public ResponseEntity<List<Notificacao>> listarTodos() {
        List<Notificacao> notificacoes = notificacaoService.listarTodos();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar uma notificação pelo ID.")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable Long id) {
        Optional<Notificacao> notificacao = notificacaoService.buscarPorId(id);
        return notificacao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar nova notificação.")
    public ResponseEntity<Notificacao> salvar(@RequestBody NotificacaoDTO notificacaoDTO) {
        Notificacao novaNotificacao = notificacaoService.salvar(notificacaoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNotificacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar notificação.")
    public ResponseEntity<Notificacao> atualizar(@PathVariable Long id, @RequestBody NotificacaoDTO notificacaoDTO) {
        Notificacao notificacaoAtualizada = notificacaoService.atualizar(id, notificacaoDTO);
        return ResponseEntity.ok(notificacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir notificação.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notificacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}