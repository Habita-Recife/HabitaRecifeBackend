package br.com.habita_recife.habita_recife_backend.exception;

public class SindicoDuplicadoException extends RuntimeException {
    public SindicoDuplicadoException(String emailSindico) {
        super("Já existe um sindico com este email: " + emailSindico);
    }
    public SindicoDuplicadoException() {
        super("Já existe um sindico ");
    }


}
