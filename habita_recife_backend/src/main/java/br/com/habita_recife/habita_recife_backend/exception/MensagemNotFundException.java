package br.com.habita_recife.habita_recife_backend.exception;

public class MensagemNotFundException extends RuntimeException {
    public MensagemNotFundException(Long idMensagem) {
        super("Mensagem não encontrado com id: " + idMensagem);
    }

    public MensagemNotFundException() {
        super("Mensagem não encontrado");
    }
}
