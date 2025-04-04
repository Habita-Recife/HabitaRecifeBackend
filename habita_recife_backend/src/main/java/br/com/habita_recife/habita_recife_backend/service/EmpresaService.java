package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.EmpresaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;

import java.util.List;
import java.util.Optional;

public interface EmpresaService {

    List<Empresa> listarTodos();
    Optional<Empresa> buscarPorId(Long id);
    Empresa salvar(EmpresaDTO empresaDTO);
    Empresa atualizar(Long id, EmpresaDTO empresaDTO);
    void excluir(Long id);
}
