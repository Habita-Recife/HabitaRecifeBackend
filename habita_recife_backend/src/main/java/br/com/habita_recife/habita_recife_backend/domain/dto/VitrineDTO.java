package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoVitrine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VitrineDTO {

    private Long id_vitrine;
    private String nome_produto;
    private TipoVitrine tipoVitrine ;
    private Double valor_produto;
    private String descricao_produto;


}
