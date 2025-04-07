package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsAdmin;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsPorteiro;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsSindico;
import br.com.habita_recife.habita_recife_backend.service.PorteiroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/porteiro")
@Tag(name = "Porteiro", description = "Gericiamento do Porteiro cadastrado.")
@IsPorteiro
public class PorteiroController {

    private final PorteiroService porteiroService;

    public PorteiroController(PorteiroService porteiroService) {
        this.porteiroService = porteiroService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('PORTEIRO', 'SINDICO')")
    @Operation(summary = "Listar", description = "Listar todos os porteiros.")
    public ResponseEntity<List<Porteiro>> listarTodos() {
        return ResponseEntity.ok(porteiroService.listarTodos());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('PORTEIRO', 'SINDICO')")
    @Operation(summary = "Buscar por ID", description = "Buscar um porteiro pelo ID.")
    public ResponseEntity<Porteiro> buscarPorId(@PathVariable Long id) {
        Optional<Porteiro> porteiro = porteiroService.buscarPorId(id);
        return porteiro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @IsSindico
    @Operation(summary = "Salvar", description = "Cadastrar novo porteiro.")
    public ResponseEntity<Porteiro> salvar(@RequestBody PorteiroDTO porteiroDTO) {
        Porteiro novoPorteiro = porteiroService.salvar(porteiroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPorteiro);
    }

    @PutMapping("/{id}")
    @IsSindico
    @Operation(summary = "Atualizar", description = "Atualizar porteiro.")
    public ResponseEntity<Porteiro> atualizar(@PathVariable Long id, @RequestBody PorteiroDTO porteiroDTO) {
        Porteiro porteiroAtualizado = porteiroService.atualizar(id,
                porteiroDTO);
        return ResponseEntity.ok(porteiroAtualizado);
    }

    @DeleteMapping("/{id}")
    @IsSindico
    @Operation(summary = "Excluir", description = "Excluir porteiro.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        porteiroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}