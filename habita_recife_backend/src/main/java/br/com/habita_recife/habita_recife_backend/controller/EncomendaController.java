package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;
import br.com.habita_recife.habita_recife_backend.service.EncomendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/encomenda")
@Tag(name = "Encomenda", description = "Gestão de encomenda")
public class EncomendaController {

    private final EncomendaService encomendaService;

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todas as encomendas.")
    public ResponseEntity<List<Encomenda>> listarTodos() {
        List<Encomenda> encomendas = encomendaService.listarTodos();
        return ResponseEntity.ok(encomendas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar encomenda pelo ID.")
    public ResponseEntity<Encomenda> buscarPorId(@PathVariable Long id) {
        Optional<Encomenda> encomenda = encomendaService.buscarPorId(id);
        return encomenda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar encomenda.")
    public ResponseEntity<Encomenda> salvar(@RequestBody EncomendaDTO encomendaDTO) {
        Encomenda novaEncomenda = encomendaService.salvar(encomendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEncomenda);
    }

    @PutMapping("/{id}")
    @Operation(summary = "atualizar", description = "Atualizar encomenda.")
    public ResponseEntity<Encomenda> atualizar(@PathVariable Long id, @RequestBody EncomendaDTO encomendaDTO) {
        Encomenda encomendaAtualizada = encomendaService.atualizar(id, encomendaDTO);
        return ResponseEntity.ok(encomendaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir encomenda.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        encomendaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}