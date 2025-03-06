package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.MoradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorServiceImpl implements MoradorService {

    private final MoradorRepository moradorRepository;

    private final CondominioRepository condominioRepository;

    @Autowired
    public MoradorServiceImpl(MoradorRepository moradorRepository, CondominioRepository condominioRepository) {
        this.moradorRepository = moradorRepository;
        this.condominioRepository = condominioRepository;
    }

    @Override
    public List<Morador> listarTodos() {
        return moradorRepository.findAll();
    }

    @Override
    public Optional<Morador> buscarPorId(Long id) {
        return moradorRepository.findById(id);
    }

    @Override
    public Morador salvar(MoradorDTO moradorDTO) {
        Optional<Condominio> optionalCondominio = condominioRepository.findById(moradorDTO.getId_condominio());

        if (!optionalCondominio.isPresent()) {
            throw new CondominioNotFoundException("Condomínio não encontrado com id: " + moradorDTO.getId_condominio());
        }

        Optional<Sindico> existingSindico = moradorRepository.findByCondominio(optionalCondominio.get());
        if (existingSindico.isPresent()) {
            throw new RuntimeException("Já existe um síndico para este condomínio.");
        }


        Condominio condominio = optionalCondominio.get();
        return null;
    }

    @Override
    public Morador atualizar(Long id, MoradorDTO moradorDTO) {
        return null;
    }

    @Override
    public void excluir(Long id) {

    }
}
