package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.NotificacaoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Notificacao;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.NotificacaoRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.service.NotificacaoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoServiceImpl implements NotificacaoService {

    private final NotificacaoRepository notificacaoRepository;
    private final MoradorRepository moradorRepository;
    private final SindicoRepository sindicoRepository;

    public NotificacaoServiceImpl(NotificacaoRepository notificacaoRepository, MoradorRepository moradorRepository, SindicoRepository sindicoRepository) {
        this.notificacaoRepository = notificacaoRepository;
        this.moradorRepository = moradorRepository;
        this.sindicoRepository = sindicoRepository;
    }


    @Override
    public List<Notificacao> listarTodos() {
        return notificacaoRepository.findAll();
    }

    @Override
    public Optional<Notificacao> buscarPorId(Long id) {
        return notificacaoRepository.findById(id);
    }

    @Override
    public Notificacao salvar(NotificacaoDTO notificacaoDTO) {

        Notificacao notificacao = new Notificacao();
        notificacao.setTipoNotificacao(notificacaoDTO.getTipoNotificacao());

        if (notificacaoDTO.getIdMorador() != null) {
            moradorRepository.findById(notificacaoDTO.getIdMorador())
                    .ifPresent(notificacao::setMorador);
        }

        if (notificacaoDTO.getIdSindico() != null) {
            sindicoRepository.findById(notificacaoDTO.getIdSindico())
                    .ifPresent(notificacao::setSindico);
        }

        return notificacaoRepository.save(notificacao);
    }

    @Override
    public Notificacao atualizar(Long id, NotificacaoDTO notificacaoDTO) {
        return notificacaoRepository.findById(id)
                .map(notificacaoExistente -> {
                    notificacaoExistente.setTipoNotificacao(notificacaoDTO.getTipoNotificacao());


                    if (notificacaoDTO.getIdMorador() != null) {
                        moradorRepository.findById(notificacaoDTO.getIdMorador())
                                .ifPresent(notificacaoExistente::setMorador);
                    } else {
                        notificacaoExistente.setMorador(null);
                    }

                    if (notificacaoDTO.getIdSindico() != null) {
                        sindicoRepository.findById(notificacaoDTO.getIdSindico())
                                .ifPresent(notificacaoExistente::setSindico);
                    } else {
                        notificacaoExistente.setSindico(null);
                    }

                    return notificacaoRepository.save(notificacaoExistente);
                })
                .orElse(null);
    }

    @Override
    public void excluir(Long id) {
        notificacaoRepository.deleteById(id);
    }
}