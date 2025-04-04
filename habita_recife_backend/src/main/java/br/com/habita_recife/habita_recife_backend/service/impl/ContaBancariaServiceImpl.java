package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.ContaBancariaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.ContaBancariaRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.FinanceiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.ContaBancariaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class ContaBancariaServiceImpl implements ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final FinanceiroRepository financeiroRepository;
    private final SindicoRepository sindicoRepository;


    public ContaBancariaServiceImpl(ContaBancariaRepository contaBancariaRepository, FinanceiroRepository financeiroRepository, SindicoRepository sindicoRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.financeiroRepository = financeiroRepository;
        this.sindicoRepository = sindicoRepository;
    }

    @Override
    public List<ContaBancaria> listarTodos() {
        return contaBancariaRepository.findAll();    }

    @Override
    public Optional<ContaBancaria> buscarPorId(Long id) {
        return contaBancariaRepository.findById(id);    }

    @Override
    public ContaBancaria salvar(ContaBancariaDTO contaBancariaDTO) {
        Optional<Financeiro> financeiroOptional = financeiroRepository.findById(contaBancariaDTO.getIdContaBancaria());

        if (financeiroOptional.isPresent()) {
            throw new CondominioNotFoundException("Financeiro não encontrado com id: " + financeiroRepository.getReferenceById());
        }

        Optional<ContaBancaria> exitingContaBancaria = contaBancariaRepository.findByFinanceiro(financeiroOptional.get());
        if (exitingContaBancaria.isPresent()) {
            throw new RuntimeException("Já existe uma Conta Bancaria para este condomínio.");
        }

        Financeiro financeiro = financeiroOptional.get();

        if (financeiroOptional.isPresent()) {
            Financeiro financeiro = financeiroOptional.get();

            ContaBancaria contaBancaria = new ContaBancaria();
            contaBancaria.setIdContaBancaria(contaBancariaDTO.getIdContaBancaria());
            contaBancaria.setAgencia(contaBancariaDTO.getAgencia());
            contaBancaria.setNumeroConta(contaBancariaDTO.getNumeroConta());
            contaBancaria.setBanco(contaBancariaDTO.getBanco());
            contaBancaria.setFinanceiro(financeiro);

            return contaBancariaRepository.save(contaBancaria);
        } else {
        throw new RuntimeException("Prefeitra não encontrada com o ID: " + id);
            }
        }

    @Override
    public ContaBancaria atualizar(Long id, ContaBancariaDTO contaBancariaDTO) {

        ContaBancaria contaBancariaAtualizar = contaBancariaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("conta não encontrada com ID: " + id));

        contaBancariaAtualizar.setAgencia(contaBancariaDTO.getAgencia());
        contaBancariaAtualizar.setIdContaBancaria(contaBancariaDTO.getIdContaBancaria());

        return contaBancariaRepository.save(contaBancariaAtualizar);
    }

    @Override
    public void excluir(Long id) {
        contaBancariaRepository.deleteById(id);
    }
}





