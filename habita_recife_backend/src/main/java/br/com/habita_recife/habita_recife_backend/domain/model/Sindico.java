package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_sindico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sindico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sindico;

    @Column(name = "nome_sindico", nullable = false, unique = true)
    private String nomeSindico;

    @Column(name = "email_sindico", nullable = false, unique = true)
    private String emailSindico;

    @Column(name = "telefone_sindico", nullable = false, length = 15)
    private String telefoneSindico;

    @Column(name = "rg_sindico", nullable = false, length = 10, unique = true)
    private String rgSindico;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Relatorio> relatorios;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio",
            foreignKey = @ForeignKey(name = "id_sindico_condominio_fk"))
    @JsonBackReference
    private Condominio condominio;

    @OneToMany(mappedBy = "sindico",cascade = CascadeType.ALL, fetch =
            FetchType.LAZY)
    @JsonManagedReference
    private List<Solicitacao> solicitacao;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Servico> servicos;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Financeiro> financeiros;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <Notificacao> notificacao;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ConfirmacaoServico> confirmacao_servicos;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Vitrine> vitrines;
}
