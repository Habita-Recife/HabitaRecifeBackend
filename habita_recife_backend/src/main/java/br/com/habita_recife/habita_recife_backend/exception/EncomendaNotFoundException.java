package br.com.habita_recife.habita_recife_backend.exception;

public class EncomendaNotFoundException extends RuntimeException {
    public EncomendaNotFoundException(Long idEncomenda) {
        super("Encomenda não encontrado com id: " + idEncomenda);
    }

    public EncomendaNotFoundException() {
        super("Encomenda não encontrado ");
    }
}
