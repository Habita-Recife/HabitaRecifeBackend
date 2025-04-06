package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.ConfirmacaoServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import br.com.habita_recife.habita_recife_backend.domain.repository.ConfirmacaoServicoRepository;
import br.com.habita_recife.habita_recife_backend.service.ConfirmacaoServicoService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ConfirmacaoServicoServiceImpl implements ConfirmacaoServicoService {

    private final ConfirmacaoServicoRepository confirmacaoServicoRepository;


    public ConfirmacaoServicoServiceImpl(ConfirmacaoServicoRepository confirmacaoServicoRepository) {
        this.confirmacaoServicoRepository = confirmacaoServicoRepository;
    }

    @Override
    public List<ConfirmacaoServico> listarTodos() {
        return confirmacaoServicoRepository.findAll();
    }

    @Override
    public Optional<ConfirmacaoServico> buscarPorId(Long id) {

        return confirmacaoServicoRepository.findById(id);
    }
    @Override
    public ConfirmacaoServico salvar(ConfirmacaoServicoDTO confirmacaoservicoDTO) {

        ConfirmacaoServico confirmacaoServico = new ConfirmacaoServico();
        confirmacaoServico.setStatusConfirmacao(confirmacaoServico.getStatusConfirmacao());
        return confirmacaoServicoRepository.save(confirmacaoServico);

    }

    @Override
    public ConfirmacaoServico atualizar(Long id, ConfirmacaoServicoDTO confirmacaoServicoDTO) {
        ConfirmacaoServico confirmacaoAtualizada = new ConfirmacaoServico();
        return confirmacaoServicoRepository.save(confirmacaoAtualizada);
    }

    @Override
    public void excluir(Long id) {



    }
}
