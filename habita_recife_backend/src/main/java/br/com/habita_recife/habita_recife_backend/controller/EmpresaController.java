package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.EmpresaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;
import br.com.habita_recife.habita_recife_backend.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarTodos() {
        List<Empresa> empresas = empresaService.listarTodos();
        return ResponseEntity.ok(empresas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.buscarPorId(id);
        return empresa.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Empresa> salvar(@RequestBody EmpresaDTO empresaDTO) {
        Empresa novoEmpresa = empresaService.salvar(empresaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoEmpresa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresaAtualizado = empresaService.atualizar(id, empresaDTO);
        return ResponseEntity.ok(empresaAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        empresaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
