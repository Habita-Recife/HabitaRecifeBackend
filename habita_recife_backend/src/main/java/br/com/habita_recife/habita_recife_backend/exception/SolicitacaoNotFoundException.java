package br.com.habita_recife.habita_recife_backend.exception;

public class SolicitacaoNotFoundException extends RuntimeException {
    public SolicitacaoNotFoundException(Long id_solicitacao) {
        super("Solicitação não encontrado com id: " + id_solicitacao);
    }

    public SolicitacaoNotFoundException() {
        super("Solicitação não encontrado");
    }
}