package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.MensagemDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Mensagem;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.repository.MensagemRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.MoradorRepository;
import br.com.habita_recife.habita_recife_backend.domain.repository.SindicoRepository;
import br.com.habita_recife.habita_recife_backend.service.MensagemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MensagemServiceImpl implements MensagemService {

    private final MensagemRepository mensagemRepository;
    private final SindicoRepository sindicoRepository;
    private final MoradorRepository moradorRepository;


    public MensagemServiceImpl(MensagemRepository mensagemRepository, SindicoRepository sindicoRepository, MoradorRepository moradorRepository) {
        this.mensagemRepository = mensagemRepository;
        this.sindicoRepository = sindicoRepository;
        this.moradorRepository = moradorRepository;
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
                .orElseThrow(() -> new RuntimeException("Síndico não encontrado"));

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
                .orElseThrow(() -> new RuntimeException("Mensagem não encontrada com ID: " + id));
        mensagemExistente.setDataMensagem(mensagemDTO.getDataMensagem());
        mensagemExistente.setTipoMensagem(mensagemDTO.getTipoMensagem());
        mensagemExistente.setTitulo(mensagemDTO.getTitulo());
        mensagemExistente.setConteudo(mensagemDTO.getConteudo());

        return mensagemRepository.save(mensagemExistente);
    }

    @Override
    public void excluir(Long id) {
        if (!mensagemRepository.existsById(id)) {
            throw new RuntimeException("Mensagem não encontrada com ID: " + id);
        }
        mensagemRepository.deleteById(id);

    }
}
