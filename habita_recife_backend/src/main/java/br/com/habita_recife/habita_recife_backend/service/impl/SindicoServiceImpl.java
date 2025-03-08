package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.SindicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SindicoServiceImpl implements SindicoService {

    private final SindicoRepository sindicoRepository;

    private final CondominioRepository condominioRepository;

    @Autowired
    public SindicoServiceImpl(SindicoRepository sindicoRepository, CondominioRepository condominioRepository) {
        this.sindicoRepository = sindicoRepository;
        this.condominioRepository = condominioRepository;
    }

    @Override
    public List<Sindico> listarTodos() {
        return sindicoRepository.findAll();
    }

    @Override
    public Optional<Sindico> buscarPorId(Long id) {
        return sindicoRepository.findById(id);
    }

    @Override
    public Sindico salvar(SindicoDTO sindicoDTO) {
        Optional<Condominio> optionalCondominio = condominioRepository.findById(sindicoDTO.getIdCondominio());

        if (!optionalCondominio.isPresent()) {
            throw new CondominioNotFoundException("Condomínio não encontrado com id: " + sindicoDTO.getIdCondominio());
        }

        Optional<Sindico> existingSindico = sindicoRepository.findByCondominio(optionalCondominio.get());
        if (existingSindico.isPresent()) {
            throw new RuntimeException("Já existe um síndico para este condomínio.");
        }


        Condominio condominio = optionalCondominio.get();

        Sindico sindico = new Sindico();
        sindico.setNomeSindico(sindicoDTO.getNomeSindico());
        sindico.setEmailSindico(sindicoDTO.getEmailSindico());
        sindico.setTelefoneSindico(sindicoDTO.getTelefoneSindico());
        sindico.setRgSindico(sindicoDTO.getRgSindico());
        sindico.setCondominio(condominio);

        return sindicoRepository.save(sindico);
    }

    @Override
    public Sindico atualizar(Long id, SindicoDTO sindicoDTO) {
        Sindico sindicoExistente = sindicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sindico não encontrado com id: " + id));

        //Não façam essa tratamento so para a entidade sindico
        if (sindicoRepository.findByEmailSindico(sindicoDTO.getEmailSindico()).isPresent() &&
                !sindicoExistente.getEmailSindico().equals(sindicoDTO.getEmailSindico())) {
            throw new IllegalArgumentException("Já existe um síndico com este e-mail: " + sindicoDTO.getEmailSindico());
        }

        sindicoExistente.setNomeSindico(sindicoDTO.getNomeSindico());
        sindicoExistente.setEmailSindico(sindicoDTO.getEmailSindico());
        sindicoExistente.setTelefoneSindico(sindicoDTO.getTelefoneSindico());

        return sindicoRepository.save(sindicoExistente);

    }

    @Override
    public void excluir(Long id) {
        Sindico sindico = sindicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Síndico não encontrado id"));

        // Desvincula o síndico do condomínio antes de deletar
        Condominio condominio = sindico.getCondominio();
        if (condominio != null) {
            condominio.setSindico(null);
            condominioRepository.save(condominio);
        }

        sindicoRepository.delete(sindico);
    }
}
