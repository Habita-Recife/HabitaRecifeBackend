package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.ContaBancariaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/contaBancaria")
@Tag(name = "Conta Bancaria", description = "Gerenciamento da conta bancaria")
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;

    public ContaBancariaController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @GetMapping
    @Operation(summary = "Listar", description = "Listar todos as contas bancarias.")
    public ResponseEntity<List<ContaBancaria>> listarTodos() {
        List<ContaBancaria> contas = contaBancariaService.listarTodos();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar connta bancaria por ID.")
    public ResponseEntity<ContaBancaria> buscarPorId(@PathVariable Long id) {
        Optional<ContaBancaria> conta = contaBancariaService.buscarPorId(id);
        return conta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar conta bancaria.")
    public ResponseEntity<ContaBancaria> salvar(@RequestBody ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria novaConta = contaBancariaService.salvar(contaBancariaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "atualizar", description = "Atualizar conta bancaria.")
    public ResponseEntity<ContaBancaria> atualizar(@PathVariable Long id, @RequestBody ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaAtualizada = contaBancariaService.atualizar(id, contaBancariaDTO);
        return ResponseEntity.ok(contaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir conta bancaria.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contaBancariaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}