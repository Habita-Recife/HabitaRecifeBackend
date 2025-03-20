package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitanteDTO {

    private Long idVisitante;
    private Long idFluxo;
    private String cpfVisitante;
    private String nomeVisitante;
    private String numeroTelefone;
    private Status statusVisitante;
}
