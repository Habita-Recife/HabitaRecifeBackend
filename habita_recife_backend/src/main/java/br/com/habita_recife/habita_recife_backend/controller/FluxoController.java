package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Fluxo;
import br.com.habita_recife.habita_recife_backend.service.FluxoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/fluxo")
public class FluxoController {

    private final FluxoService fluxoService;

    public FluxoController(FluxoService fluxoService) {
        this.fluxoService = fluxoService;
    }

    @GetMapping
    public ResponseEntity<List<Fluxo>> listarTodos() {
        return ResponseEntity.ok(fluxoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fluxo> buscarPorId(@PathVariable Long id) {
        Optional<Fluxo> fluxo = fluxoService.buscarPorId(id);
        return fluxo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/entrada")
    public ResponseEntity<Fluxo> registrarEntrada(@RequestBody FluxoDTO fluxoDTO) {
        Fluxo novoFluxo = fluxoService.registrarEntrada(fluxoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFluxo);
    }

    @PostMapping("/saida")
    public ResponseEntity<Fluxo> registrarSaida(@RequestBody FluxoDTO fluxoDTO) {
        Fluxo novoFluxo = fluxoService.registrarSaida(fluxoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFluxo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        fluxoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
