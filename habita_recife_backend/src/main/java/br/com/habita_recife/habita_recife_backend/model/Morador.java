package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.TipoMorador;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_morador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Morador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_morador;

    @Column(nullable = false, unique = true)
    private String nome_morador;

    @Column
    private String veiculo;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMorador tipo_morador;

    @Column(nullable = false, length = 11, unique = true)
    @Setter(AccessLevel.NONE)
    private String cpf_morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cobranca", nullable = false,
            foreignKey = @ForeignKey(name = "id_cobranca"))
    private Cobranca cobranca;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fluxo> fluxos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio", nullable = false,
            foreignKey = @ForeignKey(name = "id_condominio"))
    private Condominio condominio;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificacao> notificacao;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Solicitacao> solicitacao;
}