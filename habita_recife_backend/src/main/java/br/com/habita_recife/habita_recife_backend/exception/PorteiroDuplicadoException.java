package br.com.habita_recife.habita_recife_backend.exception;

public class PorteiroDuplicadoException extends RuntimeException {
    public PorteiroDuplicadoException(String cpfPorteiro) {
        super("Já existe um porteiro com este CPF: " + cpfPorteiro);
    }
    public PorteiroDuplicadoException () {
        super("Já existe um porteiro " );
    }
}
