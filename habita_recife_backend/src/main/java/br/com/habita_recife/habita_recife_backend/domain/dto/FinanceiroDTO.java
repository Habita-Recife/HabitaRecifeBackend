package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoCobranca;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMovimentacao;


import java.time.LocalDateTime;

public class FinanceiroDTO {

    private Long id_financeiro;
    private Double valor_cobranca;
    private LocalDateTime data_cobranca;
    private TipoCobranca tipoCobranca;
    private TipoMovimentacao tipoMovimentacao;

}