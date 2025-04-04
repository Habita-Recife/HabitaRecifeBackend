package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoVitrine;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "tb_vitrines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vitrine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vitrine;

    @Column(nullable = false)
    private String nome_produto;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_vitrine",nullable = false)
    private TipoVitrine tipoVitrine;

    @Column(nullable = false)
    private String descricao_produto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="###,###.00")
    private Double valor_produto;

    @ManyToOne
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "id_vitrine_sindico_fk"))
    private Sindico sindico;

}

