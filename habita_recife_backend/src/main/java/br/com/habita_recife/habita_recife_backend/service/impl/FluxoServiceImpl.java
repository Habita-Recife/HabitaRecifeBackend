package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.FluxoRepository;
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

        Fluxo fluxoEntrada = fluxoRepository.findTopByVisitanteOrderByDataFluxoDesc(visitante);
        if (fluxoEntrada == null || fluxoEntrada.getStatusFluxo() != Status.ATIVO || fluxoEntrada.getTipoFluxo() != TipoFluxo.ENTRADA) {
            throw new RuntimeException("Não há uma entrada ativa para este visitante.");
        }

        fluxoEntrada.setStatusFluxo(Status.INATIVO);
        fluxoRepository.save(fluxoEntrada);

        Fluxo fluxoSaida = new Fluxo();
        fluxoSaida.setTipoFluxo(TipoFluxo.SAIDA);
        fluxoSaida.setStatusFluxo(Status.INATIVO);
        fluxoSaida.setDataFluxo(LocalDateTime.now());
        fluxoSaida.setVisitante(visitante);
        fluxoSaida.setPorteiro(fluxoEntrada.getPorteiro());

        visitante.setStatusVisitante(Status.INATIVO);
        visitanteRepository.save(visitante);

        return fluxoRepository.save(fluxoSaida);
    }

    @Override
    public void excluir(Long id) {
        Fluxo fluxo = fluxoRepository.findById(id).orElseThrow(() -> new RuntimeException("Fluxo não " + "encontrado com id: " + id));

        Porteiro porteiro = fluxo.getPorteiro();
        if (porteiro != null) {
            porteiro.getFluxos().remove(fluxo);
            porteiroRepository.save(porteiro);
        }

        fluxoRepository.deleteById(id);

    }
}