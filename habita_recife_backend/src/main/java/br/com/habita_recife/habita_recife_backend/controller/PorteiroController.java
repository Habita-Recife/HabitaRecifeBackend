package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/porteiro")
public class PorteiroController {

    private final PorteiroService porteiroService;

    @Autowired
    public PorteiroController(PorteiroService porteiroService) {
        this.porteiroService = porteiroService;
    }

    @GetMapping
    public ResponseEntity<List<Porteiro>> listarTodos() {
        return ResponseEntity.ok(porteiroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Porteiro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(porteiroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Porteiro> salvar(@RequestBody PorteiroDTO porteiroDTO) {
        Porteiro novoPorteiro = porteiroService.salvar(novoPorteiro));
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPorteiro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Porteiro> atualizar(@PathVariable Long id, @RequestBody PorteiroDTO porteiroDTO) {
        Porteiro porteiroAtualizado = porteiroService.atualizar(id, porteiroDTO);
        return ResponseEntity.ok(porteiroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        porteiroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}