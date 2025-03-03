package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;

import java.util.List;
import java.util.Optional;

public interface SindicoService {
    List<Sindico> listarTodos();
    Optional<Sindico> buscarPorId(Long id);
    Sindico salvar(SindicoDTO sindicoDTO);
    Sindico atualizar(Long id, SindicoDTO sindicoDTO);
    void excluir(Long id);
}
