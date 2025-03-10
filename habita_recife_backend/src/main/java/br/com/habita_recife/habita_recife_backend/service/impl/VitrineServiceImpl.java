package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.VitrineDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import br.com.habita_recife.habita_recife_backend.domain.repository.VitrineRepository;
import br.com.habita_recife.habita_recife_backend.service.VitrineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitrineServiceImpl implements VitrineService  {

    private final VitrineRepository vitrineRepository;

    public VitrineServiceImpl(VitrineRepository vitrineRepository) {
        this.vitrineRepository = vitrineRepository;
    }

    @Override
    public List<Vitrine> listarTodos() {
        return vitrineRepository.findAll();
    }

    @Override
    public Optional<Vitrine> buscarPorId(Long id) {
        return vitrineRepository.findById(id);
    }

    @Override
    public Vitrine salvar(VitrineDTO vitrineDTO) {
        Vitrine vitrine = new Vitrine();
        return vitrineRepository.save(vitrine);
    }

    @Override
    public Vitrine atualizar(Long id, VitrineDTO vitrineDTO) {
        Vitrine vitrineExistente = vitrineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vitrine não encontrada com ID: " + id));

        vitrineExistente.setNome_produto(vitrineDTO.getNome_produto());
        vitrineExistente.setValor_produto(vitrineDTO.getValor_produto());
        vitrineExistente.setDescricao_produto(vitrineDTO.getDescricao_produto());

        return vitrineRepository.save(vitrineExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!vitrineRepository.existsById(id)) {
            throw new RuntimeException("Vitrine não encontrada com ID: " + id);
        }
        vitrineRepository.deleteById(id);
    }
}
