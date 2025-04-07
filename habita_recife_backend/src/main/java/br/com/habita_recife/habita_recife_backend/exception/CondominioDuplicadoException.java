package br.com.habita_recife.habita_recife_backend.exception;

public class CondominioDuplicadoException extends RuntimeException{
     public CondominioDuplicadoException(String nomeCondominio){
        super("Já existe um condominio com este nome: " + nomeCondominio);
    }
}
