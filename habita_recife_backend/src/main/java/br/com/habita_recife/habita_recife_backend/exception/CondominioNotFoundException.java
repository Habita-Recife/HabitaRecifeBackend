package br.com.habita_recife.habita_recife_backend.exception;

public class CondominioNotFoundException extends RuntimeException {
    public CondominioNotFoundException(Long idCondominio) {
        super("Condomínio não encontrado com id: " + idCondominio);
    }

    public CondominioNotFoundException() {
        super("Condomínio não encontrado");
    }
}
