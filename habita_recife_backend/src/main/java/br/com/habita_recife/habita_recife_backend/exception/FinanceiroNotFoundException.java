package br.com.habita_recife.habita_recife_backend.exception;

public class FinanceiroNotFoundException extends RuntimeException {
    public FinanceiroNotFoundException(Long id_financeiro) {
        super("Financeiro não encontrado com id: " + id_financeiro);
    }

    public FinanceiroNotFoundException() {
        super(" Financeiro não encontrado ");
    }
}
