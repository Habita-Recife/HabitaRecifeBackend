package br.com.habita_recife.habita_recife_backend.exception;

public class PorteiroNotFoundException extends RuntimeException {
    public PorteiroNotFoundException(Long idPorteiro) {
        super("Porteiro não encontrado com id: " + idPorteiro);
    }

    public PorteiroNotFoundException() {
        super("Porteiro não encontrado");
    }
}
