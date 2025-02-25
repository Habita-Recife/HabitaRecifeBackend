package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.TipoServico;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_servico;

    @Enumerated(EnumType.STRING)
    private TipoServico tipo_servico;

    @Column(nullable = false)
    private BigDecimal valor_servico;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_contrato;

    @Column(nullable = false, unique = true)
    private Integer funcionarios_alocados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sindico_id", nullable = false,
            foreignKey = @ForeignKey(name = "sindico_id"))
    private Sindico sindico;



}
