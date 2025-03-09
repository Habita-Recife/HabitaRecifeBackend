package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.VisitanteDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;

import java.util.List;
import java.util.Optional;

public interface VisitanteService {
    List<Visitante> listarTodos();
    Optional<Visitante> buscarPorId(Long id);
    Visitante salvar(VisitanteDTO visitanteDTO);
    Visitante atualizar(Long id, VisitanteDTO visitanteDTO);
    void excluir(Long id);
}
