package br.com.habita_recife.habita_recife_backend.exception;

public class PrefeituraNotFoundException extends RuntimeException {
    public PrefeituraNotFoundException(Long idPrefeitura) {
        super("Prefeitura não encontrado com id: " + idPrefeitura);
    }

    public PrefeituraNotFoundException() {
        super("Porteiro não encontrado");
    }
}