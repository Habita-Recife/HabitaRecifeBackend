package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.CondominioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.CondominioRepository;
import br.com.habita_recife.habita_recife_backend.service.CondominioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CondominioServiceImpl implements CondominioService {

    private final CondominioRepository condominioRepository;

    @Autowired
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
        // Busca um condomínio com o mesmo nome ou id
        Optional<Condominio> condominioOptional = condominioRepository
                .findByNomeCondominioOrId(condominioDTO.getNomeCondominio(), condominioDTO.getIdCondominio());

        // Validação: impede duplicação de nome
        if (condominioOptional.isPresent() &&
                (condominioDTO.getIdCondominio() == null || !condominioOptional.get().getIdCondominio().equals(condominioDTO.getIdCondominio()))) {
            throw new IllegalArgumentException("Já existe um condomínio com este nome: " + condominioDTO.getNomeCondominio());
        }

        // Criação do novo objeto Condominio a partir do DTO
        Condominio condominio = new Condominio();
        condominio.setNomeCondominio(condominioDTO.getNomeCondominio());
        condominio.setEnderecoCondominio(condominioDTO.getEnderecoCondominio());

        // Salva o objeto e retorna o condomínio criado
        return condominioRepository.save(condominio);
    }

    @Override
    public Condominio atualizar(Long id, CondominioDTO condominioDTO) {
        Condominio condominioExistente = condominioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Condomínio não encontrado com id: " + id));

        // Verifica se já existe outro condomínio com o mesmo nome
        Optional<Condominio> condominioOptional = condominioRepository
                .findByNomeCondominioOrId(condominioDTO.getNomeCondominio(), id);
        if (condominioOptional.isPresent() && !condominioOptional.get().getIdCondominio().equals(id)) {
            throw new IllegalArgumentException("Já existe um condomínio com este nome: " + condominioDTO.getNomeCondominio());
        }

        // Atualiza os dados do condomínio existente com as informações do DTO
        condominioExistente.setNomeCondominio(condominioDTO.getNomeCondominio());
        condominioExistente.setEnderecoCondominio(condominioDTO.getEnderecoCondominio());

        // Salva e retorna o condomínio atualizado
        return condominioRepository.save(condominioExistente);
    }

    @Override
    public void excluir(Long id) {
        Condominio condominio = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Condomínio não encontrado"));
        // Se houver relacionamento com Síndico, desassocia-o para evitar violação de integridade.
        if (condominio.getSindico() != null) {
            condominio.getSindico().setCondominio(null);
        }
        // Poderia ser necessário tratar a exclusão dos moradores ou outros relacionamentos.
        condominioRepository.delete(condominio);
    }
}
