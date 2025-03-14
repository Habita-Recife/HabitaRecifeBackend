package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoCobranca;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMovimentacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name= "tb_financeiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_financeiro;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###,###.00")
    private Double valor_cobranca;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_cobranca;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cobranca", nullable = false)
    private TipoCobranca tipoCobranca;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cobranca_morador_fk"))
    private Morador morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cobranca_sindico_fk"))
    private Sindico sindico;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_conta_bancaria", nullable = true,
            foreignKey = @ForeignKey(name = "id_financeiro_conta_bancaria_fk"))
    @JsonBackReference
    private ContaBancaria contaBancaria;
}
