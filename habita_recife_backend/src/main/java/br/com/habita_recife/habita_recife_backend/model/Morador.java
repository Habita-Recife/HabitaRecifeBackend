package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.TipoMorador;
import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(EnumType.ORDINAL)
    private TipoMorador tipo_morador;

    @Column(nullable = false, length = 11, unique = true)
    @Setter(AccessLevel.NONE)
    private String cpf_morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cobranca", nullable = false,
            foreignKey = @ForeignKey(name = "id_cobranca"))
    private Cobranca cobranca;

    // Relacionamento com Fluxo (1 Morador pode ter vários Movimentos dentro de fluxo que o porteiro controla)
    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fluxo> fluxos;

   /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominio_id", nullable = false,
            foreignKey = @ForeignKey(name = "condominio_id"))
    private Condominio condominio; */

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "confirmacao_id", nullable = false,
            foreignKey = @ForeignKey(name = "confirmacao_id"))
    private Comfirmacao_Servico confirmacao_servico;*/
    //falta a relação de solicitação aqui tbm

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Notificacao notificacao;
}

}
