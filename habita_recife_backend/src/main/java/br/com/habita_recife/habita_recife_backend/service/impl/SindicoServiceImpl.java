package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.SindicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.exception.SindicoDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.SindicoNotFoundException;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.service.SindicoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SindicoServiceImpl implements SindicoService {

    private final SindicoRepository sindicoRepository;

    private final CondominioRepository condominioRepository;

    private final UserRepository userRepository;

    public SindicoServiceImpl(SindicoRepository sindicoRepository,
                              CondominioRepository condominioRepository, UserRepository userRepository) {
        this.sindicoRepository = sindicoRepository;
        this.condominioRepository = condominioRepository;
        this.userRepository = userRepository;

    }

    @Override
    @Transactional(readOnly = true)
    public List<Sindico> listarTodos() {
        return sindicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sindico> buscarPorId(Long id) {
        return sindicoRepository.findById(id);
    }

    @Override
    @Transactional
    public Sindico salvar(SindicoDTO sindicoDTO) {
        Optional<Condominio> optionalCondominio = condominioRepository.findById(sindicoDTO.getIdCondominio());

        if (!optionalCondominio.isPresent()) {
            throw new CondominioNotFoundException();
        }

        Optional<Sindico> existingSindico = sindicoRepository.findByCondominio(optionalCondominio.get());
        if (existingSindico.isPresent()) {
            throw new SindicoDuplicadoException();
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
    @Transactional
    public Sindico atualizar(Long id, SindicoDTO sindicoDTO) {
        Sindico sindicoExistente = sindicoRepository.findById(id)
                .orElseThrow(() -> new SindicoNotFoundException(id));

        if (sindicoRepository.findByEmailSindico(sindicoDTO.getEmailSindico()).isPresent() &&
                !sindicoExistente.getEmailSindico().equals(sindicoDTO.getEmailSindico())) {
            throw new SindicoDuplicadoException(sindicoDTO.getEmailSindico());
        }

        Optional<User> optionalUser = userRepository.findByEmail(sindicoExistente.getEmailSindico());

        sindicoExistente.setNomeSindico(sindicoDTO.getNomeSindico());
        sindicoExistente.setEmailSindico(sindicoDTO.getEmailSindico());
        sindicoExistente.setTelefoneSindico(sindicoDTO.getTelefoneSindico());

        optionalUser.ifPresent(user -> {
            user.setEmail(sindicoDTO.getEmailSindico());
            userRepository.save(user);
        });

        return sindicoRepository.save(sindicoExistente);

    }

    @Override
    @Transactional
    public void excluir(Long id) {
        Sindico sindico = sindicoRepository.findById(id)
                .orElseThrow(() -> new SindicoNotFoundException(id));

        Condominio condominio = sindico.getCondominio();
        if (condominio != null) {
            condominio.setSindico(null);
            condominioRepository.save(condominio);
        }

        sindicoRepository.delete(sindico);
    }
}
