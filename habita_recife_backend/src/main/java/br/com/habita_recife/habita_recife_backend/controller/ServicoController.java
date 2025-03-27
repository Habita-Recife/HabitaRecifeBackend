package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.ServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Servico;
import br.com.habita_recife.habita_recife_backend.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/servico")
@Tag(name = "Serviço", description = "Gerenciamento dos serviços")
public class ServicoController {
    private final ServicoService servicoService;

    @Autowired
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos os serviços.")
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> servicos = servicoService.listarTodos();
        return ResponseEntity.ok(servicos); // Corrected return object
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar serviço por ID.")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarPorId(id);
        return servico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar serviço.")
    public ResponseEntity<Servico> salvar(@RequestBody ServicoDTO servicoDTO) {
        Servico novoServico = servicoService.salvar(servicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoServico);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar status de serviço.")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody ServicoDTO servicoDTO) {
        Servico servicoAtualizado = servicoService.atualizar(id, servicoDTO);
        if(servicoAtualizado == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(servicoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir Serviços.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}