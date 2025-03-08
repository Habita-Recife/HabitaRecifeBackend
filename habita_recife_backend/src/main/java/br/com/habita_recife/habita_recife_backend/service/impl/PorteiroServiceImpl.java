package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.PorteiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PorteiroServiceImpl implements PorteiroService {

    private final PorteiroRepository porteiroRepository;
    private final CondominioRepository condominioRepository;

    @Autowired
    public PorteiroServiceImpl(PorteiroRepository porteiroRepository, CondominioRepository condominioRepository) {
        this.porteiroRepository = porteiroRepository;
        this.condominioRepository = condominioRepository;
    }

    @Override
    public List<Porteiro> listarTodos() {
        return porteiroRepository.findAll();
    }

    @Override
    public Optional<Porteiro> buscarPorId(Long id) {
        return porteiroRepository.findById(id);
    }

    @Override
    public Porteiro salvar(PorteiroDTO porteiroDTO) {
        Optional<Condominio> optionalCondominio =
                condominioRepository.findById(porteiroDTO.getId_condominio());

        if (!optionalCondominio.isPresent()) {
            throw new CondominioNotFoundException("Condomínio não encontrado " +
                    "com id: " + porteiroDTO.getId_condominio());
        }

        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByIdPorteiroOrCpfPorteiro(porteiroDTO.getIdPorteiro(),
                        porteiroDTO.getCpfPorteiro());

        if (porteiroOptional.isPresent() &&
                (porteiroDTO.getIdPorteiro() == null || !porteiroOptional.get().getCpfPorteiro().equals(porteiroDTO.getCpfPorteiro()))) {
            throw new IllegalArgumentException("Já existe um porteiro com este cpf: " + porteiroDTO.getCpfPorteiro());
        }

        Condominio condominio = optionalCondominio.get();

        Porteiro porteiro = new Porteiro();
        porteiro.setNomePorteiro(porteiroDTO.getNomePorteiro());
        porteiro.setCpfPorteiro(porteiroDTO.getCpfPorteiro());
        porteiro.setCondominio(condominio);

        return porteiroRepository.save(porteiro);
    }

    @Override
    public Porteiro atualizar(Long id, PorteiroDTO porteiroDTO) {
        Porteiro porteiroExistente = porteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porteiro não " + "encontrado com id: " + id));

        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByIdPorteiroOrCpfPorteiro(porteiroDTO.getIdPorteiro(),
                        porteiroDTO.getCpfPorteiro());
        if (porteiroRepository.findByCpfPorteiro(porteiroDTO.getCpfPorteiro()).isPresent() &&
                !porteiroExistente.getCpfPorteiro().equals(porteiroDTO.getCpfPorteiro())) {
            throw new IllegalArgumentException("Já existe um porteiro com este cpf: " + porteiroDTO.getCpfPorteiro());
        }

        porteiroExistente.setNomePorteiro(porteiroDTO.getNomePorteiro());
        porteiroExistente.setCpfPorteiro(porteiroDTO.getCpfPorteiro());
        return porteiroRepository.save(porteiroExistente);
    }

    @Override
    public void excluir(Long id) {
        Porteiro porteiro = porteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porteiro não " + "encontrado com id: " + id));

        Condominio condominio = porteiro.getCondominio();
        if (condominio != null) {
            condominio.setPorteiro(null);
            condominioRepository.save(condominio);
        }
        porteiroRepository.deleteById(id);
    }
}