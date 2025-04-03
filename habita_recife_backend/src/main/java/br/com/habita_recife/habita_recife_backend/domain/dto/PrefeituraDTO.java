package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrefeituraDTO {

    private Long idPrefeitura;
    private String nomePrefeitura;
    private String emailPrefeitura;
}
