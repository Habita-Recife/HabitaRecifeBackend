package br.com.habita_recife.habita_recife_backend.exception;

public class EmpresaNotFoundException  extends RuntimeException{
    public EmpresaNotFoundException(Long idEmpresa) {
        super("Condomínio não encontrado com id: " + idEmpresa);
    }
    public EmpresaNotFoundException(){
        super("Condomínio não encontrado");
    }
}
