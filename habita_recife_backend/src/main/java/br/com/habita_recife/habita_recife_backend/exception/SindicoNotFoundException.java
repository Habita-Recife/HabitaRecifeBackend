package br.com.habita_recife.habita_recife_backend.exception;

public class SindicoNotFoundException extends RuntimeException {
    public SindicoNotFoundException(Long id_sindico) {
        super("Sindico não encontrado com id: " + id_sindico);
    }

    public SindicoNotFoundException() {
        super("Sindico não encontrado");
    }
}
