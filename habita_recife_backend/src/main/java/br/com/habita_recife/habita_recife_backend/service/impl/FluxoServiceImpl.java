package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.FluxoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VisitanteRepository;
import br.com.habita_recife.habita_recife_backend.service.FluxoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FluxoServiceImpl implements FluxoService {

    private final FluxoRepository fluxoRepository;
    private final MoradorRepository moradorRepository;
    private final VisitanteRepository visitanteRepository;
    private final PorteiroRepository porteiroRepository;

    @Autowired
    public FluxoServiceImpl(FluxoRepository fluxoRepository, MoradorRepository moradorRepository, VisitanteRepository visitanteRepository, PorteiroRepository porteiroRepository) {
        this.fluxoRepository = fluxoRepository;
        this.moradorRepository = moradorRepository;
        this.visitanteRepository = visitanteRepository;
        this.porteiroRepository = porteiroRepository;
    }

    @Override
    public List<Fluxo> listarTodos() {
        return fluxoRepository.findAll();
    }

    @Override
    public Optional<Fluxo> buscarPorId(Long id) {
        return fluxoRepository.findById(id);
    }

    @Override
    public Fluxo salvar(FluxoDTO fluxoDTO) {
        Optional<Visitante> optionalVisitante =
                visitanteRepository.findById(fluxoDTO.getId_visitante());

        if (!optionalVisitante.isPresent()) {
            throw new RuntimeException("Visitante não encontrado com id: " + fluxoDTO.getId_visitante());
        }

        Visitante visitante = optionalVisitante.get();

        Fluxo fluxo = new Fluxo();
        fluxo.setTipoFluxo(fluxoDTO.getTipoFluxo());
        fluxo.setDataFluxo(LocalDateTime.now());
        fluxo.setVisitante(visitante);

        return fluxoRepository.save(fluxo);
    }

    @Override
    public void excluir(Long id) {
        Fluxo fluxo = fluxoRepository.findById(id).orElseThrow(() -> new RuntimeException("Fluxo não " + "encontrado com id: " + id));

        Porteiro porteiro = fluxo.getPorteiro();
        if (porteiro != null) {
            porteiro.getFluxos().remove(fluxo);
        }

        fluxoRepository.deleteById(id);

    }
}