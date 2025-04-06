package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;

import java.util.List;
import java.util.Optional;

public interface EncomendaService {
    List<Encomenda> listarTodos();
    Optional<Encomenda> buscarPorId(Long id);
    Encomenda salvar(EncomendaDTO encomendaDTO);
    Encomenda atualizar(Long id, EncomendaDTO encomendaDTO);
    void excluir(Long id);
}

