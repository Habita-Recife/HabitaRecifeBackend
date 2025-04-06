package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.ContaBancariaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.service.ContaBancariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/contaBancaria")
@CrossOrigin(origins = "*") // anotation que permite a chamada de qualquer canto, tipo o front de localhost
@Validated
public class ContaBancariaController {

    private final ContaBancariaService contaBancariaService;

    public ContaBancariaController(ContaBancariaService contaBancariaService) {
        this.contaBancariaService = contaBancariaService;
    }

    @GetMapping
    public ResponseEntity<List<ContaBancariaDTO>> listarTodos() {
        List<ContaBancaria> contas = contaBancariaService.listarTodos();
        List<ContaBancariaDTO> contaDTOs = contas.stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contaDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaBancariaDTO> buscarPorId(@PathVariable Long id) {
        return contaBancariaService.buscarPorId(id)
                .map(this::converterParaDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ContaBancariaDTO> salvar(@Valid @RequestBody ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaSalva = contaBancariaService.salvar(contaBancariaDTO);
        return ResponseEntity.ok(converterParaDTO(contaSalva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaBancariaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaAtualizada = contaBancariaService.atualizar(id, contaBancariaDTO);
        return ResponseEntity.ok(converterParaDTO(contaAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contaBancariaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private ContaBancariaDTO converterParaDTO(ContaBancaria contaBancaria) {
        ContaBancariaDTO dto = new ContaBancariaDTO();
        dto.setIdContaBancaria(contaBancaria.getIdContaBancaria());
        dto.setSaldoConta(contaBancaria.getSaldoConta());
        dto.setNumeroConta(contaBancaria.getNumeroConta());
        dto.setAgencia(contaBancaria.getAgencia());
        dto.setBanco(contaBancaria.getBanco());

        if (contaBancaria.getCondominio() != null) {
            dto.setIdCondominio(contaBancaria.getCondominio().getIdCondominio());
        } else {
            dto.setIdCondominio(null);
        }

        return dto;
    }
}
