package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.service.MoradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/morador")
@Tag(name = "Morador", description = "Gestão do Morador")
public class MoradorController {

    private final MoradorService moradorService;

    public MoradorController(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os moradores.")
    public ResponseEntity<List<Morador>> listarTodos(){
        List<Morador> moradors = moradorService.listarTodos();
        return ResponseEntity.ok(moradors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar morador pelo ID.")
    public ResponseEntity<Morador> buscarPorId(@PathVariable Long id){
        Optional<Morador> morador = moradorService.buscarPorId(id);
        return morador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Salvar", description = "Cadastrar morador.")
    public ResponseEntity<Morador> salvar(@RequestBody MoradorDTO moradorDTO){
        Morador novoMorador = moradorService.salvar(moradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMorador);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar morador.")
    public ResponseEntity<Morador> atualizar(@PathVariable Long id, @RequestBody MoradorDTO moradorDTO) {
        Morador moradorAtualizado = moradorService.atualizar(id, moradorDTO);
        return ResponseEntity.ok(moradorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir morador.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        moradorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
