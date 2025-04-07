package br.com.habita_recife.habita_recife_backend.exception;

public class MoradorDuplicadoException extends RuntimeException {
    public MoradorDuplicadoException(String emailMorador) {
        super("Já existe um morador com este e-mail: " + emailMorador);
    }
}

