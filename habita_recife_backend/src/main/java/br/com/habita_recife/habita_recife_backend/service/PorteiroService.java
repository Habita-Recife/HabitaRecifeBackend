package br.com.habita_recife.habita_recife_backend.service;

public interface PorteiroService {

    List<Porteiro> listarTodos();
    Optional<Porteiro> buscarPorId(Long id);
    Porteiro salvar(PorteiroDTO porteiroDTO);
    Porteiro atualizar(Long id, PorteiroDTO porteiroDTO);
    void excluir(Long id);
}
