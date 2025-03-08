package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.PorteiroDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import br.com.habita_recife.habita_recife_backend.domain.repository.PorteiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PorteiroServiceImpl {

    private final PorteiroRepository porteiroRepository;

    @Autowired
    public PorteiroServiceImpl(PorteiroRepository porteiroRepository) {
        this.porteiroRepository = porteiroRepository;
    }

    public List<Porteiro> listarTodos() {
        return porteiroRepository.findAll();
    }

    public Optional<Porteiro> buscarPorId(Long id) {
        return porteiroRepository.findById(id);
    }

    public Porteiro salvar(Porteiro porteiroDTO) {
        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByOrIdPorteiroOrCpf_porteiro(porteiroDTO.getIdPorteiro(), porteiroDTO.getCpf_porteiro());

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

    public Porteiro atualizar(Long id, Porteiro porteiroDTO) {
        Porteiro porteiroExistente = porteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porteiro não " + "encontrado com id: " + id));

        Optional<Porteiro> porteiroOptional = porteiroRepository
                .findByOrIdPorteiroOrCpf_porteiro(porteiroDTO.getIdPorteiro(), porteiroDTO.getCpf_porteiro());
        if (porteiroOptional.isPresent() &&
                (porteiroDTO.getIdPorteiro() == null || !porteiroOptional.get().getIdPorteiro().equals(porteiroDTO.getIdPorteiro()))) {
            throw new IllegalArgumentException("Já existe um porteiro com " +
                    "este id: " + porteiroDTO.getIdPorteiro());
        }

        porteiroExistente.setNome_porteiro(porteiroDTO.getNome_porteiro());
        porteiroExistente.setCpf_porteiro(porteiroDTO.getCpf_porteiro());
        return porteiroRepository.save(porteiroExistente);
    }

    public void excluir(Long id) {
        Porteiro porteiro = porteiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Porteiro não " + "encontrado com id: " + id));
        /*if (porteiro.getFluxo() != null) {
            porteiro.getFluxo().setFluxo(null);*/
        porteiroRepository.deleteById(id);
    }
}