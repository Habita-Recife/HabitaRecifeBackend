package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.CondominioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.exception.CondominioDuplicadoException;
import br.com.habita_recife.habita_recife_backend.exception.CondominioNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.CondominioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondominioServiceImpl implements CondominioService {

    private final CondominioRepository condominioRepository;

    public CondominioServiceImpl(CondominioRepository condominioRepository) {
        this.condominioRepository = condominioRepository;
    }

    @Override
    public List<Condominio> listarTodos() {
        return condominioRepository.findAll();
    }

    @Override
    public Optional<Condominio> buscarPorId(Long id) {
        return condominioRepository.findById(id);
    }

    @Override
    public Condominio salvar(CondominioDTO condominioDTO) {
        Optional<Condominio> condominioOptional = condominioRepository
                .findByNomeCondominioOrIdCondominio(condominioDTO.getNomeCondominio(), condominioDTO.getIdCondominio());

        if (condominioOptional.isPresent() &&
                (condominioDTO.getIdCondominio() == null || !condominioOptional.get().getIdCondominio().equals(condominioDTO.getIdCondominio()))) {
            throw new CondominioDuplicadoException(condominioDTO.getNomeCondominio());
        }

        Condominio condominio = new Condominio();
        condominio.setNomeCondominio(condominioDTO.getNomeCondominio());
        condominio.setEnderecoCondominio(condominioDTO.getEnderecoCondominio());
        condominio.setNumeroApartamento(condominioDTO.getNumeroApartamento());
        condominio.setNumeroBloco(condominioDTO.getNumeroBloco());

        return condominioRepository.save(condominio);
    }

    @Override
    public Condominio atualizar(Long id, CondominioDTO condominioDTO) {
        Condominio condominioExistente = condominioRepository.findById(id)
                .orElseThrow(() -> new CondominioNotFoundException(id));

        Optional<Condominio> condominioOptional = condominioRepository
                .findByNomeCondominioOrIdCondominio(condominioDTO.getNomeCondominio(), id);
        if (condominioOptional.isPresent() && !condominioOptional.get().getIdCondominio().equals(id)) {
            throw new CondominioDuplicadoException(condominioDTO.getNomeCondominio());
        }

        condominioExistente.setNomeCondominio(condominioDTO.getNomeCondominio());
        condominioExistente.setEnderecoCondominio(condominioDTO.getEnderecoCondominio());

        return condominioRepository.save(condominioExistente);
    }

    @Override
    public void excluir(Long id) {
        Condominio condominio = buscarPorId(id)
                .orElseThrow(() -> new CondominioNotFoundException());
        if (condominio.getSindico() != null) {
            condominio.getSindico().setCondominio(null);
        }

        condominioRepository.delete(condominio);
    }
}
