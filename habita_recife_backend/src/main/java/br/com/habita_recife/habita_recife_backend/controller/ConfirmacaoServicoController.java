package br.com.habita_recife.habita_recife_backend.controller;
import br.com.habita_recife.habita_recife_backend.domain.dto.ConfirmacaoServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import br.com.habita_recife.habita_recife_backend.service.ConfirmacaoServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/confirmacaoservico")
public class ConfirmacaoServicoController {

    private final ConfirmacaoServicoService confirmacaoService;

    public ConfirmacaoServicoController(ConfirmacaoServicoService confirmacaoService) {
        this.confirmacaoService = confirmacaoService;
    }
    @GetMapping
    public ResponseEntity<List<ConfirmacaoServico>> listarTodos() {
        List<ConfirmacaoServico> confirmacaoServico = confirmacaoService.listarTodos();
        return ResponseEntity.ok(confirmacaoServico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfirmacaoServico> buscarPorId(@PathVariable Long id) {
        Optional<ConfirmacaoServico> confirmacaoServico = confirmacaoService.buscarPorId(id);
        return confirmacaoServico.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<ConfirmacaoServico> salvar(@RequestBody ConfirmacaoServicoDTO confirmacaoServicoDTO){
        ConfirmacaoServico novaconfirmacao = confirmacaoService.salvar(confirmacaoServicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaconfirmacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfirmacaoServico> atualizar(@PathVariable Long id, @RequestBody ConfirmacaoServicoDTO confirmacaoServicoDTO) {
        ConfirmacaoServico confirmacaoAtualizada = confirmacaoService.atualizar(id, confirmacaoServicoDTO);
        return ResponseEntity.ok(confirmacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        confirmacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
