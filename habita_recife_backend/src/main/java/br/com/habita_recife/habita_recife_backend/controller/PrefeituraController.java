package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.PrefeituraDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.meta_anotacao.IsPrefeitura;
import br.com.habita_recife.habita_recife_backend.service.PrefeituraService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/prefeitura")
@Tag(name = "Prefeitura", description = "Gericiamento do Prefeitura cadastrado.")
public class PrefeituraController {

    private final PrefeituraService prefeituraService;

    public PrefeituraController(PrefeituraService prefeituraService) {
        this.prefeituraService = prefeituraService;
    }

    @GetMapping
    @IsPrefeitura
    public ResponseEntity<List<Prefeitura>> listarTodos() {
        List<Prefeitura> prefeituras = prefeituraService.listarTodos();
        return ResponseEntity.ok(prefeituras);
    }

    @GetMapping("/{id}")
    @IsPrefeitura
    public ResponseEntity<Prefeitura> buscarPorId(@PathVariable Long id) {
        Optional<Prefeitura> prefeitura = prefeituraService.buscarPorId(id);
        return prefeitura.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @IsPrefeitura
    public ResponseEntity<Prefeitura> salvar(@RequestBody PrefeituraDTO prefeituraDTO) {
        Prefeitura novoPrefeitura = prefeituraService.salvar(prefeituraDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPrefeitura);
    }

    @PutMapping("/{id}")
    @IsPrefeitura
    public ResponseEntity<Prefeitura> atualizar(@PathVariable Long id, @RequestBody PrefeituraDTO prefeituraDTO) {
        Prefeitura prefeituraAtualizado = prefeituraService.atualizar(id, prefeituraDTO);
        return ResponseEntity.ok(prefeituraAtualizado);
    }

    @DeleteMapping("/{id}")
    @IsPrefeitura
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        prefeituraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
