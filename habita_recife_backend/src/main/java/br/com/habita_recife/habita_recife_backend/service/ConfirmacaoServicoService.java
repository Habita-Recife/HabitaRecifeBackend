package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.ConfirmacaoServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import java.util.List;
import java.util.Optional;

public interface ConfirmacaoServicoService {

    List<ConfirmacaoServico> listarTodos();
    Optional<ConfirmacaoServico> buscarPorId(Long id);
    ConfirmacaoServico salvar(ConfirmacaoServicoDTO confirmacaoservicoDTO);
    ConfirmacaoServico atualizar(Long id, ConfirmacaoServicoDTO confirmacaoServicoDTO);
    void excluir(Long id);
}