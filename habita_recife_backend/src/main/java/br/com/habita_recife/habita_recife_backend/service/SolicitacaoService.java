package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.SolicitacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;

import java.util.List;
import java.util.Optional;

public interface SolicitacaoService {
    List<Solicitacao> listarTodos();
    Optional<Solicitacao> buscarPorId(Long id);
    Optional<Solicitacao> buscarPorTitulo(String titulo);
    Solicitacao salvar(SolicitacaoDTO solicitacaoDTO);
    Solicitacao atualizar(Long id, SolicitacaoDTO solicitacaoDTO);
    void excluir(Long id);
    void verificarLimitesSolicitacoes(Long idMorador);

    Solicitacao aprovar(Long id);

    Solicitacao recusar(Long id);
}
