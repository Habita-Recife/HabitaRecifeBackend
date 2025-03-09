package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMorador;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoradorDTO {

    private Long id_condominio;
    private String nomeMorador;
    private String emailMorador;
    private String veiculoMorador;
    private TipoMorador tipoMorador;
    private String cpfMorador;
}
