package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.CondominioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.service.CondominioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/condominio")
public class CondominioController {

    private final CondominioService condominioService;

    public CondominioController(CondominioService condominioService) {
        this.condominioService = condominioService;
    }

    @GetMapping
    public ResponseEntity<List<Condominio>> listarTodos() {
        return ResponseEntity.ok(condominioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Condominio> buscarPorId(@PathVariable Long id) {
        Optional<Condominio> condominio = condominioService.buscarPorId(id);
        return condominio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Condominio> salvar(@RequestBody CondominioDTO condominioDTO) {
        Condominio novoCondominio = condominioService.salvar(condominioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCondominio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Condominio> atualizar(@PathVariable Long id, @RequestBody CondominioDTO condominioDTO) {
        Condominio condominioAtualizado = condominioService.atualizar(id, condominioDTO);
        return ResponseEntity.ok(condominioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        condominioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
