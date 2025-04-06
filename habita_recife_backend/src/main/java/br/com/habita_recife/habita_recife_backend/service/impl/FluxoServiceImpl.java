package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.FluxoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VisitanteRepository;
import br.com.habita_recife.habita_recife_backend.exception.FluxoNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.VisitanteDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.VisitanteNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.FluxoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FluxoServiceImpl implements FluxoService {

    private final FluxoRepository fluxoRepository;
    private final VisitanteRepository visitanteRepository;
    private final PorteiroRepository porteiroRepository;

    public FluxoServiceImpl(FluxoRepository fluxoRepository, VisitanteRepository visitanteRepository, PorteiroRepository porteiroRepository) {
        this.fluxoRepository = fluxoRepository;
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
                .orElseThrow(() -> new VisitanteNotFoundException( fluxoDTO.getIdVisitante()));

        Fluxo fluxoAtual = fluxoRepository.findTopByVisitanteOrderByDataFluxoDesc(visitante);
            if (fluxoAtual != null && fluxoAtual.getStatusFluxo() == Status.ATIVO && fluxoAtual.getTipoFluxo() == TipoFluxo.ENTRADA) {
            throw new VisitanteDuplicadoException();
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
                .orElseThrow(() -> new VisitanteNotFoundException());

        Fluxo fluxoAtual = fluxoRepository.findTopByVisitanteOrderByDataFluxoDesc(visitante);
        if (fluxoAtual != null && fluxoAtual.getStatusFluxo() != Status.ATIVO && fluxoAtual.getTipoFluxo() != TipoFluxo.ENTRADA) {
            throw new VisitanteDuplicadoException();
        }

        fluxoAtual.setTipoFluxo(TipoFluxo.SAIDA);
        fluxoAtual.setStatusFluxo(Status.INATIVO);
        fluxoAtual.setDataFluxo(LocalDateTime.now());

        return fluxoRepository.save(fluxoAtual);
    }

    @Override
    public void excluir(Long id) {
        Fluxo fluxo = fluxoRepository.findById(id).orElseThrow(() -> new FluxoNotFoundException(id));

        Porteiro porteiro = fluxo.getPorteiro();
        if (porteiro != null) {
            porteiro.getFluxos().remove(fluxo);
            porteiroRepository.save(porteiro);
        }

        fluxoRepository.deleteById(id);

    }
}