package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
import br.com.habita_recife.habita_recife_backend.exception.MoradorDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.MoradorNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.MoradorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MoradorServiceImpl implements MoradorService {

    private final MoradorRepository moradorRepository;
    private final CondominioRepository condominioRepository;
    private final UserRepository userRepository;

    public MoradorServiceImpl(MoradorRepository moradorRepository, CondominioRepository condominioRepository, UserRepository userRepository) {
        this.moradorRepository = moradorRepository;
        this.condominioRepository = condominioRepository;
        this.userRepository = userRepository;
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
        Condominio condominio = condominioRepository.findById(moradorDTO.getId_condominio())
                .orElseThrow(() ->
                        new CondominioNotFoundException());

        Morador morador = new Morador();
        morador.setNomeMorador(moradorDTO.getNomeMorador());
        morador.setEmailMorador(moradorDTO.getEmailMorador());
        morador.setVeiculoMorador(moradorDTO.getVeiculoMorador());
        morador.setTipoMorador(moradorDTO.getTipoMorador());
        morador.setCpfMorador(moradorDTO.getCpfMorador());
        morador.setCondominio(condominio);

        return moradorRepository.save(morador);
    }

    @Override
    @Transactional
    public Morador atualizar(Long id, MoradorDTO moradorDTO) {
        Morador moradorExistente = moradorRepository.findById(id)
                .orElseThrow(() -> new MoradorNotFoundException(id));

        moradorRepository.findByEmailMorador(moradorDTO.getEmailMorador()).ifPresent(m -> {
            if (!m.getIdMorador().equals(moradorExistente.getIdMorador())) {
                throw new MoradorDuplicadoException(moradorDTO.getEmailMorador());
            }
        });

        moradorExistente.setNomeMorador(moradorDTO.getNomeMorador());
        moradorExistente.setEmailMorador(moradorDTO.getEmailMorador());
        moradorExistente.setVeiculoMorador(moradorDTO.getVeiculoMorador());

        Optional<User> optionalUser = userRepository.findByEmail(moradorExistente.getEmailMorador());

        optionalUser.ifPresent(user -> {
            user.setEmail(moradorDTO.getEmailMorador());
            userRepository.save(user);
        });

        return moradorRepository.save(moradorExistente);
    }
    @Override
    public void excluir(Long id) {
        Morador morador = moradorRepository.findById(id)
                .orElseThrow(() -> new MoradorNotFoundException(id));

        Condominio condominio = morador.getCondominio();
        if (condominio != null) {
            condominio.setMorador(null);
            condominioRepository.save(condominio);
        }

        moradorRepository.delete(morador);
    }
}