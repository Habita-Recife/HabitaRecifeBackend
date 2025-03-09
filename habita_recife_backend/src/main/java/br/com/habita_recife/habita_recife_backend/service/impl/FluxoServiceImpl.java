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

        if (fluxoDTO.getIdMorador() == null &&
                fluxoDTO.getIdVisitante() == null &&
                fluxoDTO.getIdPorteiro() == null) {
            throw new IllegalArgumentException("Pelo menos um ID (morador, " +
                    "visitante ou porteiro) deve ser fornecido!");
        }

        Fluxo fluxo = new Fluxo();

        Optional<Morador> moradorOptional = moradorRepository.findById(fluxoDTO.getIdMorador());

        if (!moradorOptional.isPresent()) {
            return null;
            /*throw new MoradorNotFoundException("Morador não encontrado com " +
                    "id: " + fluxoDTO.getIdMorador());*/
        }

        Optional<Visitante> visitanteOptional = visitanteRepository.findById(fluxoDTO.getIdVisitante());

        if (!visitanteOptional.isPresent()) {
            return null;
            /*throw new VisitanteNotFoundException("Visitante não encontrado " +
                    "com id: " + fluxoDTO.getIdVisitante());*/
        }

        Optional<Porteiro> porteiroOptional = porteiroRepository.findById(fluxoDTO.getIdPorteiro());

        if (!porteiroOptional.isPresent()) {
            return null;
            /*throw new PorteiroNotFoundException("Porteiro não encontrado
            com id: " + fluxoDTO.getIdPorteiro());
             */
        }

        Optional<Fluxo> fluxoOptional = fluxoRepository.findById(fluxoDTO.getIdFluxo());
        if (fluxoOptional.isPresent()) {
            return null;
            /*throw new FluxoAlreadyExistsException("Fluxo já registrado: " +
             fluxoDTO.getIdFluxo());
             */
        }

        fluxo.setMorador(moradorOptional.get());
        fluxo.setVisitante(visitanteOptional.get());
        fluxo.setPorteiro(porteiroOptional.get());
        fluxo.setTipoFluxo(fluxoDTO.getTipoFluxo());
        fluxo.setDataFluxo(LocalDateTime.now());

        return fluxoRepository.save(fluxo);
    }

    @Override
    public void excluir(Long id) {
        Fluxo fluxo = fluxoRepository.findById(id).orElseThrow(() -> new RuntimeException("Fluxo não " + "encontrado com id: " + id));

        Morador morador = fluxo.getMorador();
        if (morador != null) {
            morador.getFluxos().remove(fluxo);
        }

        Visitante visitante = fluxo.getVisitante();
        if (visitante != null) {
            visitante.getFluxos().remove(fluxo);
        }

        Porteiro porteiro = fluxo.getPorteiro();
        if (porteiro != null) {
            porteiro.getFluxos().remove(fluxo);
        }

        fluxoRepository.deleteById(id);

    }
}