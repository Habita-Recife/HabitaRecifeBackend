package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.EncomendaDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Empresa;
import br.com.habita_recife.habita_recife_backend.domain.model.Encomenda;
import br.com.habita_recife.habita_recife_backend.domain.repository.EncomendaRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.exception.EncomendaNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.MoradorNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.PorteiroNotFoundException;
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

    @Override
    public Encomenda salvar(EncomendaDTO encomendaDTO) {
        var morador = moradorRepository.findById(encomendaDTO.getIdMorador())
                .orElseThrow(() -> new MoradorNotFoundException());
        var porteiro = porteiroRepository.findById(encomendaDTO.getIdPorteiro())
                .orElseThrow(() -> new PorteiroNotFoundException());

        Encomenda encomenda = new Encomenda();
        encomenda.setTipoEncomenda(encomendaDTO.getTipoEncomenda());
        encomenda.setDataEncomenda(encomendaDTO.getDataEncomenda());
        encomenda.setMorador(morador);
        encomenda.setPorteiro(porteiro);

        return encomendaRepository.save(encomenda);
    }


    @Override
    public Encomenda atualizar(Long id, EncomendaDTO encomendaDTO) {
        Encomenda encomenda = encomendaRepository.findById(id)
                .orElseThrow(() -> new EncomendaNotFoundException());

        var morador = moradorRepository.findById(encomendaDTO.getIdMorador())
                .orElseThrow(() -> new MoradorNotFoundException(id));
        var porteiro = porteiroRepository.findById(encomendaDTO.getIdPorteiro())
                .orElseThrow(() -> new PorteiroNotFoundException(id));

        encomenda.setTipoEncomenda(encomendaDTO.getTipoEncomenda());
        encomenda.setDataEncomenda(encomendaDTO.getDataEncomenda());
        encomenda.setMorador(morador);
        encomenda.setPorteiro(porteiro);

        return encomendaRepository.save(encomenda);
    }

    @Override
    public void excluir(Long id) {

    }
}
