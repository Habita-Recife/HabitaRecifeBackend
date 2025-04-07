package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.SolicitacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoSolicitacao;
import br.com.habita_recife.habita_recife_backend.domain.model.*;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SolicitacaoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.VitrineRepository;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final MoradorRepository moradorRepository;
    private final VitrineRepository vitrineRepository;
    private final SindicoRepository sindicoRepository;

    public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository, MoradorRepository moradorRepository, VitrineRepository vitrineRepository, SindicoRepository sindicoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.moradorRepository = moradorRepository;
        this.vitrineRepository = vitrineRepository;
        this.sindicoRepository = sindicoRepository;
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
        return solicitacaoRepository.findByTitulo(titulo);
    }


    @Override
    public Solicitacao salvar(SolicitacaoDTO solicitacaoDTO) {
        Morador morador = moradorRepository.findById(solicitacaoDTO.getIdMorador())
                .orElseThrow(() -> new RuntimeException("Morador não encontrado"));

        Sindico sindico = sindicoRepository.findById(solicitacaoDTO.getId_sindico())
                .orElseThrow(() -> new RuntimeException("Síndico não encontrado"));

        verificarLimitesSolicitacoes(morador.getIdMorador());

        Solicitacao solicitacao;
        if (solicitacaoDTO.getTipo_solicitacao() == TipoSolicitacao.RESERVA) {
            solicitacao = new SolicitacaoReserva(
                    solicitacaoDTO.getTitulo(),
                    solicitacaoDTO.getConteudo(),
                    morador,
                    solicitacaoDTO.getDataReserva(),
                    solicitacaoDTO.getTipoReserva(),
                    sindico
            );
        } else if (solicitacaoDTO.getTipo_solicitacao() == TipoSolicitacao.AVISO) {
            solicitacao = new SolicitacaoAviso(
                    solicitacaoDTO.getTitulo(),
                    solicitacaoDTO.getConteudo(),
                    morador,
                    sindico
            );
        } else if (solicitacaoDTO.getTipo_solicitacao() == TipoSolicitacao.POSTAGEM_VITRINE) {
            Vitrine vitrine = new Vitrine();
            vitrine.setNomeProduto(solicitacaoDTO.getNomeProduto());
            vitrine.setValorProduto(solicitacaoDTO.getValorProduto());
            vitrine.setDescricaoProduto(solicitacaoDTO.getDescricaoProduto());
            vitrine.setTelefoneContato(solicitacaoDTO.getTelefoneContato());
            vitrine.setMorador(morador);
            vitrine.setSindico(sindico);

            vitrine.setTipoVitrine(solicitacaoDTO.getTipoVitrine());

            vitrineRepository.save(vitrine);

            solicitacao = new SolicitacaoVitrine(
                    solicitacaoDTO.getTitulo(),
                    solicitacaoDTO.getConteudo(),
                    morador,
                    vitrine,
                    sindico
            );
        } else {
            throw new RuntimeException("Tipo de solicitação inválido");
        }


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
        solicitacao.setStatus_solicitacao(solicitacaoDTO.getStatus_solicitacao());

        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public void excluir(Long id) {
        Solicitacao solicitacao = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        solicitacao.setSindico(null);
        solicitacao.setMorador(null);

        solicitacaoRepository.delete(solicitacao);
    }

    @Override
    public void verificarLimitesSolicitacoes(Long idMorador) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime umaHoraAtras = agora.minusHours(1);
        LocalDateTime umDiaAtras = agora.minusDays(1);

        long ultimasHora = solicitacaoRepository.countSolicitacoesUltimaHora(idMorador, umaHoraAtras);
        if (ultimasHora >= 1) {
            throw new RuntimeException("Você só pode enviar uma solicitação por hora.");
        }

        long ultimasDia = solicitacaoRepository.countSolicitacoesUltimoDia(idMorador, umDiaAtras);
        if (ultimasDia >= 5) {
            throw new RuntimeException("Você atingiu o limite de 5 solicitações diárias.");
        }
    }

    @Override
    public Solicitacao aprovar(Long id) {
        Solicitacao solicitacao = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        solicitacao.setStatus_solicitacao(Status.APROVADO);
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public Solicitacao recusar(Long id) {
        Solicitacao solicitacao = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        solicitacao.setStatus_solicitacao(Status.RECUSADO);
        return solicitacaoRepository.save(solicitacao);
    }
}