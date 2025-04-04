package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.PrefeituraDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;

import java.util.List;
import java.util.Optional;

public interface PrefeituraService {

    List<Prefeitura> listarTodos();
    Optional<Prefeitura> buscarPorId(Long id);
    Prefeitura salvar(PrefeituraDTO prefeituraDTO);
    Prefeitura atualizar(Long id, PrefeituraDTO prefeituraDTO);
    void excluir(Long id);
}
