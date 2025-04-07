package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoSolicitacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitacaoDTO {

    private Long idMorador;
    private String titulo;
    private String conteudo;
    private TipoSolicitacao tipo_solicitacao;
    private Status status_solicitacao;
    private LocalDateTime dataSolicitacao;

}
