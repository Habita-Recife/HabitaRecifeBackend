package br.com.habita_recife.habita_recife_backend.exception;

public class VisitanteDuplicadoException extends RuntimeException {
    public VisitanteDuplicadoException(String cpfVisitante) {
        super("Já existe um visitante com este cpf: " + cpfVisitante);
    }
}
