package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoEncomenda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EncomendaDTO {

    private Long id_encomenda;
    private Long idMorador;
    private Long idPorteiro;
    private TipoEncomenda tipoEncomenda;
    private LocalDateTime dataEncomenda;


}

