package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.VisitanteDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import br.com.habita_recife.habita_recife_backend.service.VisitanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/visitante")
public class VisitanteController {

    private final VisitanteService visitanteService;

    public VisitanteController(VisitanteService visitanteService) {
        this.visitanteService = visitanteService;
    }

    @GetMapping
    public ResponseEntity<List<Visitante>> ListarTodos() {
        return ResponseEntity.ok(visitanteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitante> buscarPorId(@PathVariable Long id) {
        Optional<Visitante> visitante = visitanteService.buscarPorId(id);
        return visitante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Visitante> salvar(@RequestBody VisitanteDTO visitanteDTO) {
        Visitante novoVisitante = visitanteService.salvar(visitanteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVisitante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitante> atualizar(@PathVariable Long id, @RequestBody VisitanteDTO visitanteDTO) {
        Visitante visitanteAtualizado =
                visitanteService.atualizar(id, visitanteDTO);
        return ResponseEntity.ok(visitanteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        visitanteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
