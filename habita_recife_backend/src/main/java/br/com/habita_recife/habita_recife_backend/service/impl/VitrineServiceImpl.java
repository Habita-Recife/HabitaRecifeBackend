package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.VitrineDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VitrineRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.SindicoDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.VitrineNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.VitrineService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VitrineServiceImpl implements VitrineService  {

    private final VitrineRepository vitrineRepository;
    private final SindicoRepository sindicoRepository;

    public VitrineServiceImpl(VitrineRepository vitrineRepository, SindicoRepository sindicoRepository) {
        this.vitrineRepository = vitrineRepository;
        this.sindicoRepository = sindicoRepository;
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
        Sindico sindico = sindicoRepository.findById(vitrineDTO.getId_sindico())
                .orElseThrow(() -> new IllegalArgumentException("Sindico não encontrado"));

        Vitrine vitrine = new Vitrine();
        vitrine.setNomeProduto(vitrineDTO.getNomeProduto());
        vitrine.setTipoVitrine(vitrineDTO.getTipoVitrine());
        vitrine.setValorProduto(vitrineDTO.getValorProduto());
        vitrine.setDescricaoProduto(vitrineDTO.getDescricaoProduto());
        vitrine.setTelefoneContato(vitrineDTO.getTelefoneContato());
        vitrine.setSindico(sindico);

        return vitrineRepository.save(vitrine);
    }

    @Override
    public Vitrine atualizar(Long id, VitrineDTO vitrineDTO) {
        Vitrine vitrineExistente = vitrineRepository.findById(id)
                .orElseThrow(() -> new VitrineNotFoundException(id));

        vitrineExistente.setNomeProduto(vitrineDTO.getNomeProduto());
        vitrineExistente.setValorProduto(vitrineDTO.getValorProduto());
        vitrineExistente.setDescricaoProduto(vitrineDTO.getDescricaoProduto());

        return vitrineRepository.save(vitrineExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!vitrineRepository.existsById(id)) {
            throw new VitrineNotFoundException(id);
        }
        vitrineRepository.deleteById(id);
    }
}
