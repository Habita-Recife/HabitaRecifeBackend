package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoCobranca;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinanceiroDTO {

    private Long id_financeiro;
    private Long id_sindico;
    private Long idContaBancaria;
    private BigDecimal valor_cobranca;
    private LocalDateTime data_cobranca;
    private TipoCobranca tipoCobranca;
    private TipoMovimentacao tipoMovimentacao;


}