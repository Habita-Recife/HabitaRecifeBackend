package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
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
        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByOrIdPorteiroOrCpf_porteiro(porteiroDTO.getIdPorteiro(),
                        porteiroDTO.getCpf_porteiro());

        if (porteiroOptional.isPresent() &&
                (porteiroDTO.getIdPorteiro() == null || !porteiroOptional.get().getCpf_porteiro().equals(porteiroDTO.getCpf_porteiro()))) {
            throw new IllegalArgumentException("Já existe um porteiro com " +
                    "este cpf: " + porteiroDTO.getCpf_porteiro());
        }

        Porteiro porteiro = new Porteiro();
        porteiro.setNome_porteiro(porteiroDTO.getNome_porteiro());
        porteiro.setCpf_porteiro(porteiroDTO.getCpf_porteiro());

        return porteiroRepository.save(porteiro);
    }

    @Override
    public Porteiro atualizar(Long id, PorteiroDTO porteiroDTO) {
        Porteiro porteiroExistente = porteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porteiro não " + "encontrado com id: " + id));

        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByOrIdPorteiroOrCpf_porteiro(porteiroDTO.getIdPorteiro(),
                        porteiroDTO.getCpf_porteiro());
        if (porteiroOptional.isPresent() &&
                (porteiroDTO.getIdPorteiro() == null || !porteiroOptional.get().getIdPorteiro().equals(porteiroDTO.getIdPorteiro()))) {
            throw new IllegalArgumentException("Já existe um porteiro com " +
                    "este id: " + porteiroDTO.getIdPorteiro());
        }

        porteiroExistente.setNome_porteiro(porteiroDTO.getNome_porteiro());
        porteiroExistente.setCpf_porteiro(porteiroDTO.getCpf_porteiro());
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