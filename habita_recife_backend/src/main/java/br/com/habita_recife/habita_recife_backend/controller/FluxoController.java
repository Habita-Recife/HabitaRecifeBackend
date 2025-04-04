 package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Fluxo;
import br.com.habita_recife.habita_recife_backend.service.FluxoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/fluxo")
@Tag(name = "Fluxo", description = "Gerenciamento dos Fluxos")
public class FluxoController {

    private final FluxoService fluxoService;

    public FluxoController(FluxoService fluxoService) {
        this.fluxoService = fluxoService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os Fluxos.")
    public ResponseEntity<List<Fluxo>> listarTodos() {
        return ResponseEntity.ok(fluxoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar um fluxo pelo ID.")
    public ResponseEntity<Fluxo> buscarPorId(@PathVariable Long id) {
        Optional<Fluxo> fluxo = fluxoService.buscarPorId(id);
        return fluxo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/entrada")
    @Operation(summary = "Entrada", description = "Registro de entrada de um fluxo.")
    public ResponseEntity<Fluxo> registrarEntrada(@RequestBody FluxoDTO fluxoDTO) {
        Fluxo novoFluxo = fluxoService.registrarEntrada(fluxoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFluxo);
    }

    @PostMapping("/saida")
    @Operation(summary = "Saida", description = "Registro de saida de um fluxo.")
    public ResponseEntity<Fluxo> registrarSaida(@RequestBody FluxoDTO fluxoDTO) {
        Fluxo novoFluxo = fluxoService.registrarSaida(fluxoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoFluxo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir um fluxo.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        fluxoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
