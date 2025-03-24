package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FinanceiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import br.com.habita_recife.habita_recife_backend.domain.repository.FinanceiroRepository;
import br.com.habita_recife.habita_recife_backend.service.FinanceiroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroServiceImpl implements FinanceiroService {

    private final FinanceiroRepository financeiroRepository;


    public FinanceiroServiceImpl(FinanceiroRepository financeiroRepository) {
        this.financeiroRepository = financeiroRepository;
    }

    @Override
    public List<Financeiro> listarTodos() {
        return List.of();
    }

    @Override
    public Optional<Financeiro> buscarPorId(Long id) {
        return Optional.empty();
    }

    //alterar o salvar e atualizar
    @Override
    public Financeiro salvar(FinanceiroDTO financeiroDTO) {
        return null;
    }

    @Override
    public Financeiro atualizar(Long id, FinanceiroDTO financeiroDTO) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }
}