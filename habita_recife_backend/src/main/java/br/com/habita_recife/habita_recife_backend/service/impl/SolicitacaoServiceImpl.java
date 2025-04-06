package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.SolicitacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.domain.repository.SolicitacaoRepository;
import br.com.habita_recife.habita_recife_backend.exception.SolicitacaoNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    @Override
    public List<Solicitacao> listarTodos() {
        return solicitacaoRepository.findAll();
    }

    @Override
    public Optional<Solicitacao> buscarPorId(Long id) {
        return solicitacaoRepository.findById(id);
    }

    @Override
    public Optional<Solicitacao> buscarPorTitulo(String titulo) {
        return Optional.empty();
    }

    public Solicitacao salvar(SolicitacaoDTO solicitacaoDTO) {
        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setTitulo(solicitacaoDTO.getTitulo());
        solicitacao.setConteudo(solicitacaoDTO.getConteudo()); // Corrigido!
        solicitacao.setTipo_solicitacao(solicitacaoDTO.getTipo_solicitacao());
        solicitacao.setStatus_solicitacao(solicitacaoDTO.getStatus_solicitacao());

        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public Solicitacao atualizar(Long id, SolicitacaoDTO solicitacaoDTO) {
        Solicitacao solicitacaoExistente = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new SolicitacaoNotFoundException(id));

        solicitacaoExistente.setTitulo(solicitacaoDTO.getTitulo());
        solicitacaoExistente.setConteudo(solicitacaoDTO.getConteudo());

        return solicitacaoRepository.save(solicitacaoExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!solicitacaoRepository.existsById(id)) {
            throw new SolicitacaoNotFoundException(id);
        }
        solicitacaoRepository.deleteById(id);
    }
}
