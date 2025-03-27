package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.VitrineDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import br.com.habita_recife.habita_recife_backend.service.VitrineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/vitrine")
@Tag(name = "Vitrine", description = "Gerenciamento dos itens da vitrine")
public class VitrineController {

    private final VitrineService vitrineService;

    @Autowired
    public VitrineController(VitrineService vitrineService) {
        this.vitrineService = vitrineService;
    }

    @Operation(summary = "Listar Todos", description = "Lista todos os produtos/serviços cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<Vitrine>> listarTodos() {
        List<Vitrine> vitrine = vitrineService.listarTodos();
        return ResponseEntity.ok(vitrine);
    }

    @Operation(summary = "Buscar por ID", description = "Requisição de produtos ou serviços pelo ID de cadastro.")
    @GetMapping("/{id}")
    public ResponseEntity<Vitrine> buscarPorId(@PathVariable Long id) {
        Optional<Vitrine> vitrine = vitrineService.buscarPorId(id);
        return vitrine.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar produtos e serviços.")
    public ResponseEntity<Vitrine> salvar(@RequestBody VitrineDTO vitrineDTO) {
        Vitrine novaVitrine = vitrineService.salvar(vitrineDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaVitrine);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar produtos e serviços.")
    public ResponseEntity<Vitrine> atualizar(@PathVariable Long id, @RequestBody VitrineDTO vitrineDTO) {
        Vitrine vitrineAtualizada = vitrineService.atualizar(id, new VitrineDTO());
        if(vitrineAtualizada == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(vitrineAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir produtos e serviços.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        vitrineService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}

