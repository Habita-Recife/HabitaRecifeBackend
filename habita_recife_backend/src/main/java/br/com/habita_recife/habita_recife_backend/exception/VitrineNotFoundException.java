package br.com.habita_recife.habita_recife_backend.exception;

public class VitrineNotFoundException extends RuntimeException {
    public VitrineNotFoundException(Long id_Vitrine) {
        super("Vitrine nao encontrada com o id: " + id_Vitrine);
    }

}
