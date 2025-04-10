package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.RelatorioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Relatorio;
import br.com.habita_recife.habita_recife_backend.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1/relatorio")
@Tag(name = "Relatorio", description = "Gestao do relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService){
        this.relatorioService=relatorioService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os relatorios.")
    public ResponseEntity <List<Relatorio>> listarTodos(){
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar um relatorio pelo ID.")
    public ResponseEntity<Relatorio> buscarPorId(@PathVariable Long id){
        Optional<Relatorio> relatorio = relatorioService.buscarPorId(id);
        return relatorio.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar novo relatorio.")
    public ResponseEntity<Relatorio> salvar(@RequestBody RelatorioDTO relatorioDTO ) {
        Relatorio novoRelatorio= relatorioService.salvar(relatorioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRelatorio);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar relatorio.")
    public ResponseEntity<Relatorio> atualizar(@PathVariable Long id, @RequestBody RelatorioDTO relatorioDTO) {
        Relatorio relatorioAtualizado = relatorioService.atualizar(id, relatorioDTO);
        if(relatorioAtualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(relatorioAtualizado);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir relatorio.")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        relatorioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}