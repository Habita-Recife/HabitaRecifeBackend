package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.FluxoDTO;
import br.com.habita_recife.habita_recife_backend.domain.dto.VisitanteDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import br.com.habita_recife.habita_recife_backend.domain.repository.VisitanteRepository;
import br.com.habita_recife.habita_recife_backend.exception.VisitanteDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.VisitanteNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.VisitanteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitanteServiceimpl implements VisitanteService {

    private final VisitanteRepository visitanteRepository;

    public VisitanteServiceimpl(VisitanteRepository visitanteRepository) {
        this.visitanteRepository = visitanteRepository;
    }

    @Override
    public List<Visitante> listarTodos() {
        return visitanteRepository.findAll();
    }

    @Override
    public Optional<Visitante> buscarPorId(Long id) {
        return visitanteRepository.findById(id);
    }

    @Override
    public Visitante salvar(VisitanteDTO visitanteDTO) {

        Optional<Visitante> existingVisitante =
                visitanteRepository.findByCpfVisitante(visitanteDTO.getCpfVisitante());
        if (existingVisitante.isPresent()) {
            throw new VisitanteNotFoundException(visitanteDTO.getCpfVisitante());
        }

        Visitante visitante = new Visitante();
        visitante.setNomeVisitante(visitanteDTO.getNomeVisitante());
        visitante.setCpfVisitante(visitanteDTO.getCpfVisitante());
        visitante.setNumeroTelefone(visitanteDTO.getNumeroTelefone());
        visitante.setStatusVisitante(Status.ATIVO);

        Visitante salvarVisitante = visitanteRepository.save(visitante);
        FluxoDTO fluxoDTO = new FluxoDTO();
        fluxoDTO.setIdVisitante(salvarVisitante.getIdVisitante());
        fluxoDTO.setTipoFluxo(TipoFluxo.ENTRADA);

        return salvarVisitante;
    }

    @Override
    public Visitante atualizar(Long id, VisitanteDTO visitanteDTO) {
        Visitante visitanteExistente = visitanteRepository.findById(id)
                .orElseThrow(() -> new VisitanteNotFoundException(id));

        Optional<Visitante> visitanteOptional = visitanteRepository.findByCpfVisitante(visitanteDTO.getCpfVisitante());
        if (visitanteRepository.findByCpfVisitante(visitanteDTO.getCpfVisitante()).isPresent() && !visitanteExistente.getCpfVisitante().equals(visitanteDTO.getCpfVisitante())) {
            throw new VisitanteDuplicadoException(visitanteDTO.getCpfVisitante());
        }

        visitanteExistente.setNomeVisitante(visitanteDTO.getNomeVisitante());
        visitanteExistente.setCpfVisitante(visitanteDTO.getCpfVisitante());
        visitanteExistente.setNumeroTelefone(visitanteDTO.getNumeroTelefone());

        return visitanteRepository.save(visitanteExistente);
    }

    @Override
    public void excluir(Long id) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new VisitanteNotFoundException(id));

        visitanteRepository.deleteById(id);
    }
}
