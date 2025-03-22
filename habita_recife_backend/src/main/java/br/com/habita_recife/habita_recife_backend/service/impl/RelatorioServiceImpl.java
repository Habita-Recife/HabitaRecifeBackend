package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.RelatorioDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Relatorio;
import br.com.habita_recife.habita_recife_backend.domain.repository.RelatorioRepository;
import br.com.habita_recife.habita_recife_backend.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioServiceImpl implements RelatorioService {

    private final RelatorioRepository relatorioRepository;

    @Autowired
    public RelatorioServiceImpl(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    @Override
    public List<Relatorio> listarTodos() {
        return relatorioRepository.findAll();
    }

    @Override
    public Optional<Relatorio> buscarPorId() {
        return Optional.empty();
    }

    @Override
    public Optional<Relatorio> buscarPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    @Override
    public Relatorio salvar(RelatorioDTO relatorioDTO) {
        Relatorio relatorio = new Relatorio();
        relatorio.setData_relatorio(relatorioDTO.getData_relatorio());
        relatorio.setConteudo_relatorio(relatorioDTO.getConteudo_relatorio());
        relatorio.setTitulo(relatorioDTO.getTitulo());

        return relatorioRepository.save(relatorio);
    }

    @Override
    public Relatorio atualizar(Long id, RelatorioDTO relatorioDTO) {
        Relatorio relatorioAtualizado= relatorioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Relatorio nao encontrado pelo id"+id));
        relatorioAtualizado.setConteudo_relatorio(relatorioDTO.getConteudo_relatorio());
        relatorioAtualizado.setData_relatorio(relatorioDTO.getData_relatorio());
        relatorioAtualizado.setTitulo(relatorioDTO.getTitulo());

        return relatorioRepository.save(relatorioAtualizado);
    }

    @Override
    public void excluir(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new RuntimeException("Relatorio não encontrado com ID: " + id);
        }
        relatorioRepository.deleteById(id);
    }

}