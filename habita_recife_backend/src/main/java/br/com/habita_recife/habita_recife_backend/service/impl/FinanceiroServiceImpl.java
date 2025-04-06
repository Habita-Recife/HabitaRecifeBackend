package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FinanceiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMovimentacao;
import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.ContaBancariaRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.FinanceiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.service.ContaBancariaService;
import br.com.habita_recife.habita_recife_backend.service.FinanceiroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceiroServiceImpl implements FinanceiroService {

    private final FinanceiroRepository financeiroRepository;
    private final SindicoRepository sindicoRepository;
    private final ContaBancariaRepository contaBancariaRepository;
    private final ContaBancariaService contaBancariaService;

    public FinanceiroServiceImpl(
            FinanceiroRepository financeiroRepository,
            SindicoRepository sindicoRepository,
            ContaBancariaRepository contaBancariaRepository,
            ContaBancariaService contaBancariaService) {
        this.financeiroRepository = financeiroRepository;
        this.sindicoRepository = sindicoRepository;
        this.contaBancariaRepository = contaBancariaRepository;
        this.contaBancariaService = contaBancariaService;
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

    }

//
//    @Override
//    public Financeiro salvar(FinanceiroDTO financeiroDTO) {
//        Sindico sindico = sindicoRepository.findById(financeiroDTO.getId_sindico())
//                .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com ID: " + financeiroDTO.getId_sindico()));
//
//        ContaBancaria contaBancaria = contaBancariaRepository.findById(financeiroDTO.getIdContaBancaria())
//                .orElseThrow(() -> new EntityNotFoundException("Conta bancária não encontrada com ID: " + financeiroDTO.getIdContaBancaria()));
//
//        Financeiro financeiro = new Financeiro();
//        financeiro.setValor_cobranca(financeiroDTO.getValor_cobranca());
//        financeiro.setTipoCobranca(financeiroDTO.getTipoCobranca());
//        financeiro.setData_cobranca(LocalDateTime.now());
//        financeiro.setTipoMovimentacao(financeiroDTO.getTipoMovimentacao());
//        financeiro.setSindico(sindico);
//        financeiro.setContaBancaria(contaBancaria);
//
//        Financeiro salvo = financeiroRepository.save(financeiro);
//
//        contaBancariaService.atualizar(contaBancaria, salvo.getValor_cobranca(), salvo.getTipoMovimentacao());
//
//        return salvo;
//    }
//
//    @Override
//    public Financeiro atualizar(Long id, FinanceiroDTO financeiroDTO) {
//        Financeiro existente = financeiroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Financeiro não encontrado com ID: " + id));
//
//        Sindico sindico = sindicoRepository.findById(financeiroDTO.getId_sindico())
//                .orElseThrow(() -> new EntityNotFoundException("Síndico não encontrado com ID: " + financeiroDTO.getId_sindico()));
//
//        ContaBancaria contaBancaria = contaBancariaRepository.findById(financeiroDTO.getIdContaBancaria())
//                .orElseThrow(() -> new EntityNotFoundException("Conta bancária não encontrada com ID: " + financeiroDTO.getIdContaBancaria()));
//
//        contaBancariaService.atualizar(existente.getContaBancaria(), existente.getValor_cobranca().negate(), existente.getTipoMovimentacao());
//
//        existente.setValor_cobranca(financeiroDTO.getValor_cobranca());
//        existente.setTipoCobranca(financeiroDTO.getTipoCobranca());
//        existente.setData_cobranca(LocalDateTime.now());
//        existente.setTipoMovimentacao(financeiroDTO.getTipoMovimentacao());
//        existente.setSindico(sindico);
//        existente.setContaBancaria(contaBancaria);
//
//        Financeiro atualizado = financeiroRepository.save(existente);
//
//        contaBancariaService.atualizar(contaBancaria, atualizado.getValor_cobranca(), atualizado.getTipoMovimentacao());
//
//        return atualizado;
//    }
//
//    @Override
//    public void excluir(Long id) {
//        Financeiro financeiro = financeiroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Financeiro não encontrado com id: " + id));
//
//        contaBancariaService.atualizar(financeiro.getContaBancaria(), financeiro.getValor_cobranca().negate(), financeiro.getTipoMovimentacao());
//
//        financeiroRepository.delete(financeiro);
//    }
}