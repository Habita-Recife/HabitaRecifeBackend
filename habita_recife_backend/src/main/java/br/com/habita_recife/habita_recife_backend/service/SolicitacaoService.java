package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoService {
    List<Solicitacao> listarTodos();
    Optional<Solicitacao> buscarPorId(Long id);
    Solicitacao salvar(String titulo, Long id, Solicitacao solicitacao);
    Solicitacao atualizar(Long id, Solicitacao solicitacao);
    void excluir(Long id);
}
