package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMorador;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long idMorador;

    @Column(name = "nome_morador",nullable = false)
    private String nomeMorador;

    @Column(name = "email_morador", nullable = false, unique = true)
    private String emailMorador;

    @Column(name = "veiculo", unique = false)
    private String veiculoMorador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_morador", nullable = false)
    private TipoMorador tipoMorador;

    @Column(name = "cpf_morador", nullable = false, length = 11, unique = true)
    private String cpfMorador;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Financeiro> financeiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio", nullable = false,
            foreignKey = @ForeignKey(name = "id_morador_condominio_fk"))
    @JsonBackReference
    private Condominio condominio;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificacao> notificacao;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Solicitacao> solicitacao;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ConfirmacaoServico> confirmacao_servicos;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Servico> servico;
}