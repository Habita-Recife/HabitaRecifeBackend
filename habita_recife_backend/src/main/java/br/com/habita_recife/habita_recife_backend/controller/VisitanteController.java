package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.VisitanteDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import br.com.habita_recife.habita_recife_backend.service.VisitanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/visitante")
@Tag(name = "Visitante", description = "Gestão de visitantes do condominio")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os visitantes.")
    public ResponseEntity<List<Visitante>> ListarTodos() {
        return ResponseEntity.ok(visitanteService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar os visitantes pelo ID.")
    public ResponseEntity<Visitante> buscarPorId(@PathVariable Long id) {
        Optional<Visitante> visitante = visitanteService.buscarPorId(id);
        return visitante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar visitantes.")
    public ResponseEntity<Visitante> salvar(@RequestBody VisitanteDTO visitanteDTO) {
        Visitante novoVisitante = visitanteService.salvar(visitanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVisitante);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar status do visitante.")
    public ResponseEntity<Visitante> atualizar(@PathVariable Long id, @RequestBody VisitanteDTO visitanteDTO) {
        Visitante visitanteAtualizado =
                visitanteService.atualizar(id, visitanteDTO);
        return ResponseEntity.ok(visitanteAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir visitantes.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        visitanteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
