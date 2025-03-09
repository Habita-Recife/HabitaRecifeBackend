package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.ServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoService {
    List<Servico> listarTodos();
    Optional<Servico> buscarPorId(Long id);
    Servico salvar(ServicoDTO servicoDTO);
    Servico atualizar(Long id, ServicoDTO servicoDTO);
    void excluir(Long id);
}

