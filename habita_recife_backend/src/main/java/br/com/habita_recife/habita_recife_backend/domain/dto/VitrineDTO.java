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

    private Long id_sindico;
    private Long id_vitrine;
    private String nomeProduto;
    private TipoVitrine tipoVitrine ;
    private Double valorProduto;
    private String descricaoProduto;
    private String telefoneContato;
    private String bloco;
    private String apartamento;
    private String nomeMorador;


}
