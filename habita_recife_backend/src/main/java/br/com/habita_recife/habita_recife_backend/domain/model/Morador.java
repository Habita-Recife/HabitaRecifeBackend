package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMorador;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "veiculo")
    private String veiculoMorador;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_morador", nullable = false)
    private TipoMorador tipoMorador;

    @Column(name = "cpf_morador", nullable = false, length = 14, unique = true)
    private String cpfMorador;

    @Column(name = "bloco_morador", nullable = false, length = 2)
    private String bloco;

    @Column(name = "apartamento_morador", nullable = false, unique = true, length = 4)
    private String apartamento;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Financeiro> financeiro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio", nullable = false,
            foreignKey = @ForeignKey(name = "id_morador_condominio_fk"))
    @JsonBackReference
    private Condominio condominio;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Vitrine> vitrine;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notificacao> notificacao;

    @OneToMany(mappedBy = "morador",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Solicitacao> solicitacao;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ConfirmacaoServico> confirmacao_servicos;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Servico> servico;

    @OneToMany(mappedBy = "morador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Encomenda> encomendas;

}