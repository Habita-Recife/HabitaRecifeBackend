package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.ContaBancariaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.domain.repository.ContaBancariaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaBancariaService {

    List<ContaBancaria> listarTodos();
    Optional<ContaBancaria> buscarPorId(Long id);
    ContaBancaria salvar(ContaBancariaDTO contaBancariaDTO);
    ContaBancaria atualizar(Long id, ContaBancariaDTO contaBancariaDTO);
    void excluir(Long id);
}
