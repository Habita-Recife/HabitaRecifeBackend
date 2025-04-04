package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.dto.ServicoDTO;
import br.com.habita_recife.habita_recife_backend.domain.model.Servico;
import br.com.habita_recife.habita_recife_backend.domain.repository.ServicoRepository;
import br.com.habita_recife.habita_recife_backend.service.ServicoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoServiceImpl implements ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoServiceImpl(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Override
    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    @Override
    public Optional<Servico> buscarPorId(Long id) {
        return servicoRepository.findById(id);
    }

    @Override
    public Servico salvar(ServicoDTO servicoDTO) {

        Servico servicos = new Servico();
        servicos.setTipoServico(servicoDTO.getTipoServico());
        servicos.setValorServico(servicoDTO.getValorServico());
        servicos.setDataContrato(servicoDTO.getDataContrato());
        servicos.setFuncionariosAlocados(servicoDTO.getFuncionariosAlocados());

        return servicoRepository.save(servicos);
    }

    @Override
    public Servico atualizar(Long id, ServicoDTO servicoDTO) {

        Servico servicosAtualizados = servicoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Serviços não encontrado com id " + id));

        servicosAtualizados.setValorServico(servicoDTO.getValorServico());
        servicosAtualizados.setDataContrato(servicoDTO.getDataContrato());
        servicosAtualizados.setFuncionariosAlocados(servicoDTO.getFuncionariosAlocados());

        return servicoRepository.save(servicosAtualizados);
    }

    @Override
    public void excluir(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado com ID: " + id);
        }
        servicoRepository.deleteById(id);
    }

}
