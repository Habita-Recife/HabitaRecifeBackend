package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.VisitanteDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.model.Fluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import br.com.habita_recife.habita_recife_backend.domain.repository.FluxoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VisitanteRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitanteServiceimpl implements VisitanteService {

    private final VisitanteRepository visitanteRepository;
    private final FluxoRepository fluxoRepository;

    @Autowired
    public VisitanteServiceimpl(VisitanteRepository visitanteRepository, FluxoRepository fluxoRepository) {
        this.visitanteRepository = visitanteRepository;
        this.fluxoRepository = fluxoRepository;
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
            throw new RuntimeException("Já existe um visitante com este cpf: " + visitanteDTO.getCpfVisitante());
        }

        Visitante visitante = new Visitante();
        visitante.setNomeVisitante(visitanteDTO.getNomeVisitante());
        visitante.setCpfVisitante(visitanteDTO.getCpfVisitante());
        visitante.setNumeroVelefone(visitanteDTO.getNumeroVelefone());
        visitante.setStatusVisitante(Status.ATIVO);

        return visitanteRepository.save(visitante);
    }

    @Override
    public Visitante atualizar(Long id, VisitanteDTO visitanteDTO) {
        Visitante visitanteExistente = visitanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visitante não encontrado com id: " + id));

        Optional<Visitante> visitanteOptional = visitanteRepository.findByCpfVisitante(visitanteDTO.getCpfVisitante());
        if (visitanteRepository.findByCpfVisitante(visitanteDTO.getCpfVisitante()).isPresent() && !visitanteExistente.getCpfVisitante().equals(visitanteDTO.getCpfVisitante())) {
            throw new IllegalArgumentException("Já existe um visitante com" +
                    " este cpf: " + visitanteDTO.getCpfVisitante());
        }
        return visitanteRepository.save(visitanteExistente);
    }

    @Override
    public void excluir(Long id) {
        Visitante visitante = visitanteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visitante não encontrado com id: " + id));

        /*Fluxo fluxo = visitante.getFluxo();
        if (fluxo != null) {
            fluxo.setVisitante(null);
            fluxoRepository.save(fluxo);
        }*/

        fluxoRepository.deleteById(id);
    }
}
