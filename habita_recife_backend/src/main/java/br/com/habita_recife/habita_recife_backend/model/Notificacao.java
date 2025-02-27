package br.com.habita_recife.habita_recife_backend.model;


import br.com.habita_recife.habita_recife_backend.enums.TipoMorador;
import br.com.habita_recife.habita_recife_backend.enums.TipoNotificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tb_notificacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notificacao;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipo_notificacao;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "fk_notificacao_morador"))
    private Morador morador;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name="fk_notificacao_sindico"))
    private Sindico sindico;


}
