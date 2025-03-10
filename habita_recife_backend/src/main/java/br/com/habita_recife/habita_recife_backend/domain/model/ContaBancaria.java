package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoCobranca;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_conta_bancaria")
public class ContaBancaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContaBancaria;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###,###.00")
    private BigDecimal saldoConta;

    @Column(nullable = false, unique = true)
    private String numeroConta;

    @Column(nullable = false, unique = true)
    private String agencia;

    @Column(nullable = false, unique = true)
    private String banco;

    @OneToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Condominio condominio;

    @OneToOne(mappedBy = "contaBancaria", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Financeiro financeiro;
}
