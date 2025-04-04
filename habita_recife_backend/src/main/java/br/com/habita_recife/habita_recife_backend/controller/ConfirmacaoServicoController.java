package br.com.habita_recife.habita_recife_backend.controller;
import br.com.habita_recife.habita_recife_backend.domain.dto.ConfirmacaoServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import br.com.habita_recife.habita_recife_backend.service.ConfirmacaoServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/confirmacaoservico")
@Tag(name = "Confirmação de Serviços", description = "Gestão dos serviços")
public class ConfirmacaoServicoController {

    private final ConfirmacaoServicoService confirmacaoService;

    public ConfirmacaoServicoController(ConfirmacaoServicoService confirmacaoService) {
        this.confirmacaoService = confirmacaoService;
    }
    @GetMapping
    @Operation(summary = "Listar", description = "Listar todas as confirmaçoes de serviços.")
    public ResponseEntity<List<ConfirmacaoServico>> listarTodos() {
        List<ConfirmacaoServico> confirmacaoServico = confirmacaoService.listarTodos();
        return ResponseEntity.ok(confirmacaoServico);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar por ID", description = "Buscar confirmação de serviço pelo ID.")
    public ResponseEntity<ConfirmacaoServico> buscarPorId(@PathVariable Long id) {
        Optional<ConfirmacaoServico> confirmacaoServico = confirmacaoService.buscarPorId(id);
        return confirmacaoServico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar a confirmaçao de serviço.")
    public ResponseEntity<ConfirmacaoServico> salvar(@RequestBody ConfirmacaoServicoDTO confirmacaoServicoDTO){
        ConfirmacaoServico novaconfirmacao = confirmacaoService.salvar(confirmacaoServicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaconfirmacao);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar", description = "Atualizar status da confirmaçao de serviço.")
    public ResponseEntity<ConfirmacaoServico> atualizar(@PathVariable Long id, @RequestBody ConfirmacaoServicoDTO confirmacaoServicoDTO) {
        ConfirmacaoServico confirmacaoAtualizada = confirmacaoService.atualizar(id, confirmacaoServicoDTO);
        return ResponseEntity.ok(confirmacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir", description = "Excluir confirmaçao de serviço.")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        confirmacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
