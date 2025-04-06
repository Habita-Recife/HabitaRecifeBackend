package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FinanceiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import br.com.habita_recife.habita_recife_backend.domain.repository.FinanceiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.FinanceiroNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.FinanceiroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroServiceImpl implements FinanceiroService {

    private final FinanceiroRepository financeiroRepository;
    private final SindicoRepository sindicoRepository;
//    private final ContaBancariaRepository contaBancariaRepository;


    public FinanceiroServiceImpl(FinanceiroRepository financeiroRepository, SindicoRepository sindicoRepository/*ContaBancariaRepository contaBancariaRepository*/) {
        this.financeiroRepository = financeiroRepository;
        this.sindicoRepository = sindicoRepository;
//        this.contaBancariaRepository = contaBancariaRepository;
    }

    @Override
    public List<Financeiro> listarTodos() {
        return financeiroRepository.findAll();
    }

    @Override
    public Optional<Financeiro> buscarPorId(Long id) {
        return financeiroRepository.findById(id);
    }

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
        Financeiro financeiro = financeiroRepository.findById(id)
                .orElseThrow(() -> new FinanceiroNotFoundException(id));

        financeiroRepository.deleteById(id);

    }
}