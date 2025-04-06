package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;
import br.com.habita_recife.habita_recife_backend.service.EncomendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/encomenda")
public class EncomendaController {

    private final EncomendaService encomendaService;

    public EncomendaController(EncomendaService encomendaService) {
        this.encomendaService = encomendaService;
    }

    @GetMapping
    public ResponseEntity<List<Encomenda>> listarTodos() {
        List<Encomenda> encomendas = encomendaService.listarTodos();
        return ResponseEntity.ok(encomendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Encomenda> buscarPorId(@PathVariable Long id) {
        Optional<Encomenda> encomenda = encomendaService.buscarPorId(id);
        return encomenda.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Encomenda> salvar(@RequestBody EncomendaDTO encomendaDTO) {
        Encomenda novaEncomenda = encomendaService.salvar(encomendaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEncomenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Encomenda> atualizar(@PathVariable Long id, @RequestBody EncomendaDTO encomendaDTO) {
        Encomenda encomendaAtualizada = encomendaService.atualizar(id, encomendaDTO);
        return ResponseEntity.ok(encomendaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        encomendaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}