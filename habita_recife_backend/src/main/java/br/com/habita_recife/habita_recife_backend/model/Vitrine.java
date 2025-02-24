package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.Status;
import br.com.habita_recife.habita_recife_backend.enums.TipoVitrine;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "tb_vitrine")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vitrine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_produto;

    @Column(nullable = false)
    private String nome_produto;

    @Enumerated(EnumType.STRING)
    private TipoVitrine tipo_vitrine;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###,###.00")
    private Double valor_produto;

    @ManyToOne
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "id_sindico"))
    private Sindico sindico;
}

