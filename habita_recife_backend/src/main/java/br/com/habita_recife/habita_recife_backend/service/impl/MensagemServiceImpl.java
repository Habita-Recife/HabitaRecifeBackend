package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.MensagemDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Mensagem;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.MensagemRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.exception.MensagemNotFundException;
import br.com.habita_recife.habita_recife_backend.exception.SindicoNotFoundException;
import br.com.habita_recife.habita_recife_backend.service.MensagemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemServiceImpl implements MensagemService {

    private final MensagemRepository mensagemRepository;
    private final SindicoRepository sindicoRepository;


    public MensagemServiceImpl(MensagemRepository mensagemRepository,SindicoRepository sindicoRepository) {
        this.mensagemRepository = mensagemRepository;
        this.sindicoRepository = sindicoRepository;
    }

    @Override
    public List<Mensagem> listarTodos() {
        return mensagemRepository.findAll();

    }

    @Override
    public Optional<Mensagem> buscarPorId(Long id) {
        return mensagemRepository.findById(id);

    }

    @Override
    public Mensagem salvar(MensagemDTO mensagemDTO) {
        Sindico sindico = sindicoRepository.findById(mensagemDTO.getIdSindico())
                .orElseThrow(() -> new SindicoNotFoundException());

        Mensagem mensagem = new Mensagem();
        mensagem.setDataMensagem(mensagemDTO.getDataMensagem());
        mensagem.setTipoMensagem(mensagemDTO.getTipoMensagem());
        mensagem.setTitulo(mensagemDTO.getTitulo());
        mensagem.setConteudo(mensagemDTO.getConteudo());
        mensagem.setSindico(sindico);

        return mensagemRepository.save(mensagem);
    }


    @Override
    public Mensagem atualizar(Long id, MensagemDTO mensagemDTO) {
        Mensagem mensagemExistente = mensagemRepository.findById(id)
                .orElseThrow(() -> new MensagemNotFundException(id));
        mensagemExistente.setDataMensagem(mensagemDTO.getDataMensagem());
        mensagemExistente.setTipoMensagem(mensagemDTO.getTipoMensagem());
        mensagemExistente.setTitulo(mensagemDTO.getTitulo());
        mensagemExistente.setConteudo(mensagemDTO.getConteudo());

        return mensagemRepository.save(mensagemExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!mensagemRepository.existsById(id)) {
            throw new MensagemNotFundException(id);
        }
        mensagemRepository.deleteById(id);

    }
}
