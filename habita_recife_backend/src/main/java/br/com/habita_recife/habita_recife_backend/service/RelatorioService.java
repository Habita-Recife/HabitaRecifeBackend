package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.RelatorioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Relatorio;

import java.util.List;
import java.util.Optional;

public interface RelatorioService {
    List<Relatorio>listarTodos();
    Optional<Relatorio>buscarPorId();
    Optional<Relatorio> buscarPorId(Long id);
    Relatorio salvar(RelatorioDTO relatorioDTO);
    Relatorio atualizar(Long id, RelatorioDTO relatorioDTO);
    void excluir(Long id);

}