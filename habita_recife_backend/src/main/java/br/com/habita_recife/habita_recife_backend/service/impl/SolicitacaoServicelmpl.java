package br.com.habita_recife.habita_recife_backend.service.impl;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import br.com.habita_recife.habita_recife_backend.domain.repository.SolicitacaoRepository;
import br.com.habita_recife.habita_recife_backend.service.SolicitacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServicelmpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    @Autowired
    public SolicitacaoServicelmpl(SolicitacaoRepository solicitacaoRepository) {
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
    public Solicitacao salvar(String titulo, Long id, Solicitacao solicitacao) {

        Optional<Solicitacao> solicitacaoExistenteOptional = solicitacaoRepository.findByTitulo(titulo);

        if (solicitacaoExistenteOptional.isPresent()) {
            Solicitacao solicitacaoExistente = solicitacaoExistenteOptional.get();

            if (id != null && solicitacaoExistente.getId_solicitacao() != null && solicitacaoExistente.getId_solicitacao().equals(id)) {
                // Título e ID correspondem, atualiza.
                solicitacaoExistente.setTitulo(solicitacao.getTitulo());
                solicitacaoExistente.setConteudo(solicitacao.getConteudo());
                return solicitacaoRepository.save(solicitacaoExistente);
            } else if (id != null && solicitacaoExistente.getId_solicitacao() == null) {
                // Título existe, mas ID não existe.
                // Adicione aqui a lógica que você deseja para esse caso.
                // Exemplo: lançar uma exceção ou criar uma nova solicitação.
                throw new RuntimeException("Solicitação com título '" + titulo + "' encontrada, mas não possui ID.");
                //Ou
                //return solicitacaoRepository.save(solicitacao);
            } else {
                // Título existe, ID existe, mas não correspondem.
                throw new RuntimeException("Solicitação com título '" + titulo + "' encontrada, mas o ID fornecido não corresponde.");
            }
        } else {
            // Título não existe, cria nova solicitação.
            return solicitacaoRepository.save(solicitacao);
        }
    }

    @Override
    public Solicitacao atualizar(Long id, Solicitacao solicitacao) {
        Solicitacao solicitacaoExistente = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("solicitação não encontrado com id: " + id));

        solicitacaoExistente.setTitulo(solicitacao.getTitulo());
        solicitacaoExistente.setConteudo(solicitacao.getConteudo());

        Solicitacao update = solicitacaoRepository.save(solicitacaoExistente);
        return update;
    }

    @Override
    public void excluir(Long id) {
        solicitacaoRepository.deleteById(id);

    }
}


//metodo atualizar  passar pro salvar e ver qual usar no controller ,post e put