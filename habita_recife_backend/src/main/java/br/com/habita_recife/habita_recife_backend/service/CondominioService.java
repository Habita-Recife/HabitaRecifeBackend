package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.CondominioDTO;
import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;

import java.util.List;
import java.util.Optional;

public interface CondominioService {
    List<Condominio> listarTodos();
    Optional<Condominio> buscarPorId(Long id);
    Condominio salvar(CondominioDTO condominioDTO);
    Condominio atualizar(Long id, CondominioDTO condominioDTO);
    void excluir(Long id);
}
