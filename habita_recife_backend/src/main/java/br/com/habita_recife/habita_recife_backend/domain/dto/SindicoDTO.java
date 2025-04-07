package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SindicoDTO {

    private Long id_sindico;
    private Long idCondominio;
    private String nomeSindico;
    private String emailSindico;
    private String telefoneSindico;
    private String rgSindico;
}
