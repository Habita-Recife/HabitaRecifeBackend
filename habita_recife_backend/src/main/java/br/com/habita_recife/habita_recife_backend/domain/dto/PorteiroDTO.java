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

    private Long id_condominio;
    private Long idPorteiro;
    private Long id_fluxo;
    private String nomePorteiro;
    private String cpfPorteiro;
}
