package br.com.habita_recife.habita_recife_backend.exception;

public class CondominioNotFoundException extends RuntimeException {
    public CondominioNotFoundException(String message) {
        super(message);
    }
}
