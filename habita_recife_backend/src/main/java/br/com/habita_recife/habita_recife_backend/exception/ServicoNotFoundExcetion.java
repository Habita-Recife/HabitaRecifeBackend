package br.com.habita_recife.habita_recife_backend.exception;

public class ServicoNotFoundExcetion extends RuntimeException {
    public ServicoNotFoundExcetion(Long id_servico) {
        super("Serviço não encontrado com id: " + id_servico);
    }
    public ServicoNotFoundExcetion(){
        super("Serviço não encontrado");
    }
}
