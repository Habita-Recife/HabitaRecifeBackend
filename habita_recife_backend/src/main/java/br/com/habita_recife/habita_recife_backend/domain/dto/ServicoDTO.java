package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoServico;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicoDTO {

    private TipoServico tipoServico;
    private BigDecimal valorServico;
    private LocalDateTime dataContrato;
    private Integer funcionariosAlocados;

}
