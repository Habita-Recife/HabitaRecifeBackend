package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.ContaBancariaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.ContaBancariaRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.FinanceiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.ContaBancariaNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.ContaBancariaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ContaBancariaServiceImpl implements ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final FinanceiroRepository financeiroRepository;
    private final SindicoRepository sindicoRepository;
    private final CondominioRepository condominioRepository;

    public ContaBancariaServiceImpl(ContaBancariaRepository contaBancariaRepository, FinanceiroRepository financeiroRepository, SindicoRepository sindicoRepository, CondominioRepository condominioRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
        this.financeiroRepository = financeiroRepository;
        this.sindicoRepository = sindicoRepository;
        this.condominioRepository = condominioRepository;
    }

    @Override
    public List<ContaBancaria> listarTodos() {
        return contaBancariaRepository.findAll();    }

    @Override
    public Optional<ContaBancaria> buscarPorId(Long id) {
        return contaBancariaRepository.findById(id);    }



    @Override
    public ContaBancaria salvar(ContaBancariaDTO contaBancariaDTO) {
        Condominio condominio = condominioRepository.findById(contaBancariaDTO.getIdCondominio())
                .orElseThrow(() -> new CondominioNotFoundException(contaBancariaDTO.getIdCondominio()));
        ContaBancaria contaBancaria = new ContaBancaria();
        contaBancaria.setSaldoConta(contaBancariaDTO.getSaldoConta() != null ? contaBancariaDTO.getSaldoConta() : BigDecimal.ZERO);
        contaBancaria.setNumeroConta(contaBancariaDTO.getNumeroConta());
        contaBancaria.setAgencia(contaBancariaDTO.getAgencia());
        contaBancaria.setBanco(contaBancariaDTO.getBanco());

        return contaBancariaRepository.save(contaBancaria);
    }

    @Override
    public ContaBancaria atualizar(Long id, ContaBancariaDTO contaBancariaDTO) {
        ContaBancaria contaBancariaExistente = contaBancariaRepository.findById(id)
                .orElseThrow(() -> new ContaBancariaNotFoundException(id));

        Condominio condominio = condominioRepository.findById(contaBancariaDTO.getIdCondominio())
                .orElseThrow(() -> new CondominioNotFoundException(contaBancariaDTO.getIdCondominio()));

        contaBancariaExistente.setSaldoConta(contaBancariaDTO.getSaldoConta());
        contaBancariaExistente.setNumeroConta(contaBancariaDTO.getNumeroConta());
        contaBancariaExistente.setAgencia(contaBancariaDTO.getAgencia());
        contaBancariaExistente.setBanco(contaBancariaDTO.getBanco());

        return contaBancariaRepository.save(contaBancariaExistente);
    }

        @Override
    public void excluir(Long id) {
        contaBancariaRepository.deleteById(id);
    }
}





