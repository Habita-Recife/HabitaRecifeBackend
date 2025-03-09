package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Fluxo;

import java.util.List;
import java.util.Optional;

public interface FluxoService {

    List<Fluxo> listarTodos();
    Optional<Fluxo> buscarPorId(Long id);
    Fluxo salvar(FluxoDTO fluxoDTO);
    void excluir(Long id);
}
