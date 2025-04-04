package br.com.habita_recife.habita_recife_backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaBancariaDTO {

    private Long idContaBancaria;
    private BigDecimal saldoConta;
    private String numeroConta;
    private String agencia;
    private String banco;

}
