package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.FinanceiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import br.com.habita_recife.habita_recife_backend.service.FinanceiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping
    public ResponseEntity<List<Financeiro>> listarTodos() {
        List<Financeiro> financeiros = financeiroService.listarTodos();
        return ResponseEntity.ok(financeiros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Financeiro> buscarPorId(@PathVariable Long id) {
        Optional<Financeiro> financeiro = financeiroService.buscarPorId(id);
        return financeiro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Financeiro> salvar(@RequestBody FinanceiroDTO financeiroDTO) {
        Financeiro financeiro = financeiroService.salvar(financeiroDTO);
        return ResponseEntity.ok(financeiro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Financeiro> atualizar(@PathVariable Long id, @RequestBody FinanceiroDTO financeiroDTO) {
        Financeiro financeiroAtualizado = financeiroService.atualizar(id, financeiroDTO);
        return ResponseEntity.ok(financeiroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        financeiroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}