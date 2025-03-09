package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDTO {

    private Long idMorador;
    private String titulo;
    private String conteudo;
    private Enum tipo_solicitacao;
    private Enum status_solicitacao;

}
