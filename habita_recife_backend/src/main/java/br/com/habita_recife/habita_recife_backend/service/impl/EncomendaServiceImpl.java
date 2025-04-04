package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;
import br.com.habita_recife.habita_recife_backend.domain.repository.EncomendaRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.service.EncomendaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EncomendaServiceImpl implements EncomendaService {

    private final EncomendaRepository encomendaRepository;
    private final MoradorRepository moradorRepository;
    private final PorteiroRepository porteiroRepository;

    public EncomendaServiceImpl(EncomendaRepository encomendaRepository, MoradorRepository moradorRepository, PorteiroRepository porteiroRepository) {
        this.encomendaRepository = encomendaRepository;
        this.moradorRepository = moradorRepository;
        this.porteiroRepository = porteiroRepository;
    }


    @Override
    public List<Encomenda> listarTodos() {
        return List.of();
    }

    @Override
    public Optional<Encomenda> buscarPorId(Long id) {
        return Optional.empty();
    }
    //falta salvar e atualizar
    @Override
    public Empresa salvar(EncomendaDTO encomendaDTO) {
        return null;
    }

    @Override
    public Empresa atualizar(Long id, EncomendaDTO encomendaDTO) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }
}
