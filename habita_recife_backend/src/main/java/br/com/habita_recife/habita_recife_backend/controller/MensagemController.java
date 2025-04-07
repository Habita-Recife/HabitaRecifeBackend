package br.com.habita_recife.habita_recife_backend.controller;

import br.com.habita_recife.habita_recife_backend.domain.dto.MensagemDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Mensagem;
import br.com.habita_recife.habita_recife_backend.service.MensagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    @Autowired
    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @GetMapping
    public ResponseEntity<List<Mensagem>> listarTodos() {
        List<Mensagem> mensagens = mensagemService.listarTodos();
        return ResponseEntity.ok(mensagens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mensagem> buscarPorId(@PathVariable Long id) {
        Optional<Mensagem> mensagem = mensagemService.buscarPorId(id);
        return mensagem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Mensagem> salvar(@RequestBody MensagemDTO mensagemDTO) {
        Mensagem novaMensagem = mensagemService.salvar(mensagemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMensagem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mensagem> atualizar(@PathVariable Long id, @RequestBody MensagemDTO mensagemDTO) {
        Mensagem mensagemAtualizada = mensagemService.atualizar(id, mensagemDTO);
        if (mensagemAtualizada == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(mensagemAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        mensagemService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}