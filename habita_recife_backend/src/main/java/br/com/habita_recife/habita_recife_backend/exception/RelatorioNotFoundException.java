package br.com.habita_recife.habita_recife_backend.exception;

public class RelatorioNotFoundException extends RuntimeException {
    public RelatorioNotFoundException(Long id_relatorio) {
        super("Relatorio não encontrado com id: " + id_relatorio);
    }

    public RelatorioNotFoundException() {
        super("Relatorio não encontrado");
    }
}
