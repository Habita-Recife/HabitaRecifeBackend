package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FluxoDTO {

    private Long idMorador;
    private Long idVisitante;
    private Long idPorteiro;
    private Long idFluxo;
    private TipoFluxo tipoFluxo;
    private LocalDateTime dataFluxo;
}
