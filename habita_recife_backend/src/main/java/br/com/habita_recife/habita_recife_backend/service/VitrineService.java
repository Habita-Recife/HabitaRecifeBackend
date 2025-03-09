package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.domain.dto.VitrineDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import br.com.habita_recife.habita_recife_backend.domain.repository.VitrineRepository;

import java.util.List;
import java.util.Optional;

public interface VitrineService {
    List<Vitrine> listartodos();
    Optional<Vitrine> buscarPorId(Long id);
    Vitrine salvar(VitrineDTO vitrineDTO);
    Vitrine atualizar(Long id, VitrineDTO vitrineDTO);
    void excluir(Long id);
}

