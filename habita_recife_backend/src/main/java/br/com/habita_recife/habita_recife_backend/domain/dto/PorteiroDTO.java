package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PorteiroDTO {

    private Long idCondominio;
    private Long idPorteiro;
    private Long idFluxo;
    private String nomePorteiro;
    private String emailPorteiro;
    private String cpfPorteiro;
}
