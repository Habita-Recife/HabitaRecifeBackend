package br.com.habita_recife.habita_recife_backend.exception;

public class VisitanteNotFoundException extends RuntimeException {
    public VisitanteNotFoundException(Long idVisitante) {
        super("Visitante não encontrado com id: " + idVisitante);
    }

    public VisitanteNotFoundException() {
        super("Porteiro não encontrado");
    }
}
