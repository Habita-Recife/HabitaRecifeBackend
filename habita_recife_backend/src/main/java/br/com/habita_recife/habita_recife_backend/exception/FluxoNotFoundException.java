package br.com.habita_recife.habita_recife_backend.exception;

public class FluxoNotFoundException extends RuntimeException {
  public FluxoNotFoundException(Long idFluxo) {
    super("Fluxo não encontrado com id: " + idFluxo);
  }

  public FluxoNotFoundException() {
    super(" Fluxo não encontrado ");
  }
}