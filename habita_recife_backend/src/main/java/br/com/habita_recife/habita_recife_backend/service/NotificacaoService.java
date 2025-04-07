package br.com.habita_recife.habita_recife_backend.service;


import br.com.habita_recife.habita_recife_backend.domain.dto.NotificacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Notificacao;

import java.util.List;
import java.util.Optional;

public interface NotificacaoService {
    List<Notificacao> listarTodos();
    Optional<Notificacao> buscarPorId(Long id);
    Notificacao salvar(NotificacaoDTO notificacaoDTO);
    Notificacao atualizar(Long id, NotificacaoDTO notificacaoDTO);
    void excluir(Long id);
}
