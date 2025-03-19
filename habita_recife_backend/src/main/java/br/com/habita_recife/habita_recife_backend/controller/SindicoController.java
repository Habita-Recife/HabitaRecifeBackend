package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.service.SindicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/sindico")
public class SindicoController {

    private final SindicoService sindicoService;

    public SindicoController(SindicoService sindicoService) {
        this.sindicoService = sindicoService;
    }

    @GetMapping
    public ResponseEntity<List<Sindico>> listarTodos() {
        List<Sindico> sindicos = sindicoService.listarTodos();
        return ResponseEntity.ok(sindicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sindico> buscarPorId(@PathVariable Long id) {
        Optional<Sindico> sindico = sindicoService.buscarPorId(id);
        return sindico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Sindico> salvar(@RequestBody SindicoDTO sindicoDTO) {
        Sindico novoSindico = sindicoService.salvar(sindicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoSindico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sindico> atualizar(@PathVariable Long id, @RequestBody SindicoDTO sindicoDTO) {
        Sindico sindicoAtualizado = sindicoService.atualizar(id, sindicoDTO);
        return ResponseEntity.ok(sindicoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        sindicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
