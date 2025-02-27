package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.Status;
import br.com.habita_recife.habita_recife_backend.enums.TipoSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_solicitacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitacao;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 250)
    private String conteudo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSolicitacao tipo_solicitacao;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Status status_solicitacao;

    @ManyToOne
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "id_morador"))
    private Morador morador;
}
