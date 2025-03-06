package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;

import java.util.List;
import java.util.Optional;

public interface MoradorService {
    List<Morador> listarTodos();
    Optional<Morador> buscarPorId(Long id);
    Morador salvar(MoradorDTO moradorDTO);
    Morador atualizar(Long id, MoradorDTO moradorDTO);
    void excluir(Long id);

}
