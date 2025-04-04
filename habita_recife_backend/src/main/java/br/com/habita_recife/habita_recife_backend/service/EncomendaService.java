package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;

import java.util.List;
import java.util.Optional;

public interface EncomendaService {
    List<Encomenda> listarTodos();
    Optional<Encomenda> buscarPorId(Long id);
    Empresa salvar(EncomendaDTO encomendaDTO);
    Empresa atualizar(Long id, EncomendaDTO encomendaDTO);
    void excluir(Long id);
}

