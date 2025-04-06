package br.com.habita_recife.habita_recife_backend.exception;


public class MoradorNotFoundException extends RuntimeException {
    public MoradorNotFoundException(Long idMorador) {
        super("Morador não encontrado com id: " + idMorador);
    }

    public MoradorNotFoundException() {
        super("Morador não encontrado");
    }
}
