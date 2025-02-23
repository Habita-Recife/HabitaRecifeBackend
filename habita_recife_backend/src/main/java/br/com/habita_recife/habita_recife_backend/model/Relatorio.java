package br.com.habita_recife.habita_recife_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_relatorios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_relatorio;

    @Column(nullable = false, length = 1000)
    private String conteudo_relatorio;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_relatorio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefeitura_id", nullable = false,
            foreignKey = @ForeignKey(name = "prefeitura_id"))
    private Prefeitura prefeitura;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sindico_id", nullable = false,
            foreignKey = @ForeignKey(name = "sindico_id"))
    private Sindico sindico;

}
