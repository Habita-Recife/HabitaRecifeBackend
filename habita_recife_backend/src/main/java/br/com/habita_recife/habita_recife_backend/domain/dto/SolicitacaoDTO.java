package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoReserva;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoSolicitacao;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoVitrine;
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

    private Long id_sindico;
    private Long idMorador;
    private String titulo;
    private String conteudo;
    private TipoSolicitacao tipo_solicitacao;
    private Status status_solicitacao;
    private LocalDateTime dataSolicitacao;

    private LocalDateTime dataReserva;
    private TipoReserva tipoReserva;

    private String nomeProduto;
    private Double valorProduto;
    private String telefoneContato;
    private String descricaoProduto;
    private TipoVitrine tipoVitrine;

}
