package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/morador")
public class MoradorController {

    private final MoradorService moradorService;

    @Autowired
    public MoradorController(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    @GetMapping
    public ResponseEntity<List<Morador>> listarTodos(){
        List<Morador> moradors = moradorService.listarTodos();
        return ResponseEntity.ok(moradors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Morador> buscarPorId(@PathVariable Long id){
        Optional<Morador> morador = moradorService.buscarPorId(id);
        return morador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Morador> salvar(@RequestBody MoradorDTO moradorDTO){
        Morador novoMorador = moradorService.salvar(moradorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMorador);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Morador> atualizar(@PathVariable Long id, @RequestBody MoradorDTO moradorDTO) {
        Morador moradorAtualizado = moradorService.atualizar(id, moradorDTO);
        return ResponseEntity.ok(moradorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        moradorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
