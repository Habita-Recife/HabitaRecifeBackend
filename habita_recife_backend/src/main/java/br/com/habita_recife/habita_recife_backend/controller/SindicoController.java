package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.service.SindicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/sindico")
@Tag(name = "Sindico", description = "Gestão do Sindico")
public class SindicoController {

    private final SindicoService sindicoService;

    public SindicoController(SindicoService sindicoService) {
        this.sindicoService = sindicoService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os sindicos.")
    public ResponseEntity<List<Sindico>> listarTodos() {
        List<Sindico> sindicos = sindicoService.listarTodos();
        return ResponseEntity.ok(sindicos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar sindicos por ID.")
    public ResponseEntity<Sindico> buscarPorId(@PathVariable Long id) {
        Optional<Sindico> sindico = sindicoService.buscarPorId(id);
        return sindico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar Sindicos.")
    public ResponseEntity<Sindico> salvar(@RequestBody SindicoDTO sindicoDTO) {
        Sindico novoSindico = sindicoService.salvar(sindicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSindico);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar informaçoes do sindicos.")
    public ResponseEntity<Sindico> atualizar(@PathVariable Long id, @RequestBody SindicoDTO sindicoDTO) {
        Sindico sindicoAtualizado = sindicoService.atualizar(id, sindicoDTO);
        return ResponseEntity.ok(sindicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir sindicos.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        sindicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
