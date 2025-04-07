package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.MensagemDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Mensagem;

import java.util.List;
import java.util.Optional;

public interface MensagemService {
    List<Mensagem> listarTodos();
    Optional<Mensagem> buscarPorId(Long id);
    Mensagem salvar(MensagemDTO mensagemDTO);
    Mensagem atualizar(Long id, MensagemDTO mensagemDTO);
    void excluir(Long id);
}


