package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.FinanceiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;

import java.util.List;
import java.util.Optional;

public interface FinanceiroService {
    List<Financeiro> listarTodos();
    Optional<Financeiro> buscarPorId(Long id);
    Financeiro salvar(FinanceiroDTO financeiroDTO);
    Financeiro atualizar(Long id, FinanceiroDTO financeiroDTO);
    void excluir(Long id);
}