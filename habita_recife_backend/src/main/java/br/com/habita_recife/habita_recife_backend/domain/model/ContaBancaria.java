package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_conta_bancaria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
