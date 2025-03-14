package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.PrefeituraDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.service.PrefeituraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/prefeitura")
public class PrefeituraController {

    private final PrefeituraService prefeituraService;

    @Autowired
    public PrefeituraController(PrefeituraService prefeituraService) {
        this.prefeituraService = prefeituraService;
    }

    @GetMapping
    public ResponseEntity<List<Prefeitura>> listarTodos() {
        List<Prefeitura> prefeituras = prefeituraService.listarTodos();
        return ResponseEntity.ok(prefeituras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prefeitura> buscarPorId(@PathVariable Long id) {
        Optional<Prefeitura> prefeitura = prefeituraService.buscarPorId(id);
        return prefeitura.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Prefeitura> salvar(@RequestBody PrefeituraDTO prefeituraDTO) {
        Prefeitura novoPrefeitura = prefeituraService.salvar(prefeituraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPrefeitura);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prefeitura> atualizar(@PathVariable Long id, @RequestBody PrefeituraDTO prefeituraDTO) {
        Prefeitura prefeituraAtualizado = prefeituraService.atualizar(id, prefeituraDTO);
        return ResponseEntity.ok(prefeituraAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        prefeituraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
