package br.com.habita_recife.habita_recife_backend.exception;

public class ContaBancariaNotFoundException extends RuntimeException {
    public ContaBancariaNotFoundException(Long idContaBancaria) {
        super("Conta bancaria não encontrado com id: " + idContaBancaria);
    }

    public ContaBancariaNotFoundException() {
        super("Conta bancaria não encontrado ");
    }
}
