package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.FluxoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VisitanteRepository;
import br.com.habita_recife.habita_recife_backend.service.FluxoService;
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
    public Fluxo registrarEntrada(FluxoDTO fluxoDTO) {
        Visitante visitante = visitanteRepository.findById(fluxoDTO.getIdVisitante())
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado com id: " + fluxoDTO.getIdVisitante()));

        Fluxo fluxoAtual = fluxoRepository.findTopByVisitanteOrderByDataFluxoDesc(visitante);
            if (fluxoAtual != null && fluxoAtual.getStatusFluxo() == Status.ATIVO && fluxoAtual.getTipoFluxo() == TipoFluxo.ENTRADA) {
            throw new RuntimeException("O visitante já realizou uma entrada anteriormente.");
        }

        Fluxo fluxo = new Fluxo();
        fluxo.setTipoFluxo(TipoFluxo.ENTRADA);
        fluxo.setStatusFluxo(Status.ATIVO);
        fluxo.setDataFluxo(LocalDateTime.now());
        fluxo.setVisitante(visitante);

        return fluxoRepository.save(fluxo);
    }

    @Override
    public Fluxo registrarSaida(FluxoDTO fluxoDTO) {
        Visitante visitante = visitanteRepository.findById(fluxoDTO.getIdVisitante())
                .orElseThrow(() -> new RuntimeException("Visitante não encontrado com id: " + fluxoDTO.getIdVisitante()));

        Fluxo fluxoAtual = fluxoRepository.findTopByVisitanteOrderByDataFluxoDesc(visitante);
        if (fluxoAtual != null && fluxoAtual.getStatusFluxo() != Status.ATIVO && fluxoAtual.getTipoFluxo() != TipoFluxo.ENTRADA) {
            throw new RuntimeException("O visitante já realizou uma saída anteriormente.");
        }

        fluxoAtual.setTipoFluxo(TipoFluxo.SAIDA);
        fluxoAtual.setStatusFluxo(Status.INATIVO);
        fluxoAtual.setDataFluxo(LocalDateTime.now());

        return fluxoRepository.save(fluxoAtual);
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