package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;

import java.util.List;
import java.util.Optional;

public interface PorteiroService {

    List<Porteiro> listarTodos();

    Optional<Porteiro> buscarPorId(Long id);

    Porteiro salvar(PorteiroDTO porteiroDTO);

    Porteiro atualizar(Long id, PorteiroDTO porteiroDTO);

    void excluir(Long id);
}
