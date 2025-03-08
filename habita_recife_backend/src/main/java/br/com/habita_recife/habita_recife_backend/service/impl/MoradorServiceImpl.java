package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
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

        Optional<Morador> existingSindico = moradorRepository.findByCondominio(optionalCondominio.get());
        if (existingSindico.isPresent()) {
            throw new RuntimeException("Já existe um morador para este condomínio.");
        }


        Condominio condominio = optionalCondominio.get();

        Morador morador = new Morador();
        morador.setNomeMorador(moradorDTO.getNomeMorador());
        morador.setVeiculoMorador(moradorDTO.getVeiculoMorador());
        morador.setTipoMorador(moradorDTO.getTipoMorador());
        morador.setCpfMorador(moradorDTO.getCpfMorador());
        morador.setCondominio(condominio);

        return moradorRepository.save(morador);
    }

    @Override
    public Morador atualizar(Long id, MoradorDTO moradorDTO) {
        Morador moradorExistente = moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Moraodr não encontrado com id: " + id));

        //Não façam essa tratamento so para a entidade sindico
        if (moradorRepository.findByEmailMorador(moradorDTO.getEmailMorador()).isPresent() &&
                !moradorExistente.getEmailMorador().equals(moradorDTO.getEmailMorador())) {
            throw new IllegalArgumentException("Já existe um síndico com este e-mail: " + moradorDTO.getEmailMorador());
        }

        moradorExistente.setNomeMorador(moradorDTO.getNomeMorador());
        moradorExistente.setEmailMorador(moradorDTO.getEmailMorador());
        moradorExistente.setVeiculoMorador(moradorDTO.getVeiculoMorador());

        return moradorRepository.save(moradorExistente);
    }

    @Override
    public void excluir(Long id) {
        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Morador não encontrado com id."));

        Condominio condominio = morador.getCondominio();
        if (condominio != null){
            condominio.setMorador(null);
            condominioRepository.save(condominio);
        }

        moradorRepository.delete(morador);

    }
}
