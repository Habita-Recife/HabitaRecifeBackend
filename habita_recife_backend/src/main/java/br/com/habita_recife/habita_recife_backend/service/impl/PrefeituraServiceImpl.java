package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.PrefeituraDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.domain.repository.PrefeituraRepository;
import br.com.habita_recife.habita_recife_backend.exception.PrefeituraNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.PrefeituraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrefeituraServiceImpl implements PrefeituraService {

    private final PrefeituraRepository prefeituraRepository;

    public PrefeituraServiceImpl(PrefeituraRepository prefeituraRepository) {
        this.prefeituraRepository = prefeituraRepository;
    }


    @Override
    public List<Prefeitura> listarTodos() {
        return prefeituraRepository.findAll();
    }

    @Override
    public Optional<Prefeitura> buscarPorId(Long id) {
        return prefeituraRepository.findById(id);
    }

    @Override
    public Prefeitura salvar(PrefeituraDTO prefeituraDTO) {
      Prefeitura prefeitura = new Prefeitura();
      prefeitura.setNomePrefeitura(prefeituraDTO.getNomePrefeitura());
      prefeitura.setEmailPrefeitura(prefeituraDTO.getEmailPrefeitura());

       return prefeituraRepository.save(prefeitura);
    }


    @Override
    public Prefeitura atualizar(Long id, PrefeituraDTO prefeituraDTO) {
        Prefeitura prefeituraExistente = prefeituraRepository.findById(id)
                .orElseThrow(() -> new PrefeituraNotFoundException(id));

        prefeituraExistente.setNomePrefeitura(prefeituraDTO.getNomePrefeitura());
        return prefeituraRepository.save(prefeituraExistente);
    }

    @Override
    public void excluir(Long id) {

        prefeituraRepository.deleteById(id);

    }
}
