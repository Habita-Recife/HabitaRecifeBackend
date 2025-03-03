package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name= "tb_cobranca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cobranca;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###,###.00")
    private Double valor_cobranca;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_cobranca;

    @ManyToOne
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cobranca_morador_fk"))
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "fk_cobranca_sindico_fk"))
    private Sindico sindico;
}
