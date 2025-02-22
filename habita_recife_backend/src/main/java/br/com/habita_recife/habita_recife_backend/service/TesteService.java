package br.com.habita_recife.habita_recife_backend.service;

import br.com.habita_recife.habita_recife_backend.model.Prefeitura;
import br.com.habita_recife.habita_recife_backend.repository.TesteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TesteService {

    private final TesteRepository testeRepository;

    @Autowired
    public TesteService(TesteRepository testeRepository) {
        this.testeRepository = testeRepository;
    }
    public List<Prefeitura> findAll() {
        return testeRepository.findAll();
    }

    public Prefeitura save(Prefeitura prefeitura) {
        return testeRepository.save(prefeitura);
    }
}
