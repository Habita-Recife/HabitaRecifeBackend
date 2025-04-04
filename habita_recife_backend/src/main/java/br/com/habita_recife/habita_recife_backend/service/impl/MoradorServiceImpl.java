package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.MoradorDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import br.com.habita_recife.habita_recife_backend.features_authentication.repository.UserRepository;
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
        Optional<Condominio> optionalCondominio = condominioRepository.findById(moradorDTO.getId_condominio());

        if (!optionalCondominio.isPresent()) {
            throw new CondominioNotFoundException("Condomínio não encontrado com id: " + moradorDTO.getId_condominio());
        }



        Condominio condominio = optionalCondominio.get();

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
                .orElseThrow(() -> new RuntimeException("Morador não encontrado com id: " + id));

        if (moradorRepository.findByEmailMorador(moradorDTO.getEmailMorador()).isPresent() &&
                !moradorExistente.getEmailMorador().equals(moradorDTO.getEmailMorador())) {
            throw new IllegalArgumentException("Já existe um morador com este e-mail: " + moradorDTO.getEmailMorador());
        }

        Optional<User> optionalUser = userRepository.findByEmail(moradorExistente.getEmailMorador());

        moradorExistente.setNomeMorador(moradorDTO.getNomeMorador());
        moradorExistente.setEmailMorador(moradorDTO.getEmailMorador());
        moradorExistente.setVeiculoMorador(moradorDTO.getVeiculoMorador());

        optionalUser.ifPresent(user -> {
            user.setEmail(moradorDTO.getEmailMorador());
            userRepository.save(user);
        });

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
