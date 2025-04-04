package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.SolicitacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.model.Morador;
import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SolicitacaoRepository;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private MoradorRepository moradorRepository;

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
        return solicitacaoRepository.findByTitulo(titulo);
    }


    @Override
    public Solicitacao salvar(SolicitacaoDTO solicitacaoDTO) {
        Morador morador = moradorRepository.findById(solicitacaoDTO.getIdMorador())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));

        verificarLimitesSolicitacoes(morador.getIdMorador());

        Solicitacao solicitacao = new Solicitacao(
                solicitacaoDTO.getTitulo(),
                solicitacaoDTO.getConteudo(),
                solicitacaoDTO.getTipo_solicitacao(),
                morador
        );
        solicitacao.setStatus_solicitacao(solicitacaoDTO.getStatus_solicitacao() != null ?
                solicitacaoDTO.getStatus_solicitacao() : Status.PENDENTE);

        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public Solicitacao atualizar(Long id, SolicitacaoDTO solicitacaoDTO) {
        Solicitacao solicitacao = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

        solicitacao.setTitulo(solicitacaoDTO.getTitulo());
        solicitacao.setConteudo(solicitacaoDTO.getConteudo());
        solicitacao.setTipo_solicitacao(solicitacaoDTO.getTipo_solicitacao());
        solicitacao.setStatus_solicitacao(solicitacaoDTO.getStatus_solicitacao());

        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public void excluir(Long id) {
        Solicitacao solicitacao = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        solicitacaoRepository.delete(solicitacao);
    }

    @Override
    public void verificarLimitesSolicitacoes(Long moradorId) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umaHoraAtras = agora.minusHours(1);
        LocalDateTime umDiaAtras = agora.minusDays(1);

        long ultimasHora = solicitacaoRepository.countSolicitacoesUltimaHora(moradorId, umaHoraAtras);
        if (ultimasHora >= 1) {
            throw new RuntimeException("Você só pode enviar uma solicitação por hora.");
        }

        long ultimasDia = solicitacaoRepository.countSolicitacoesUltimoDia(moradorId, umDiaAtras);
        if (ultimasDia >= 5) {
            throw new RuntimeException("Você atingiu o limite de 5 solicitações diárias.");
        }
    }
}
