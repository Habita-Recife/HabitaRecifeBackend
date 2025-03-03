package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_condominio", nullable = true,
            foreignKey = @ForeignKey(name = "id_sindico_condominio_fk"))
    private Condominio condominio;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Servico> servicos;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cobranca> cobrancas;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <Notificacao> notificacao;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ConfirmacaoServico> confirmacao_servicos;

    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Vitrine> vitrines;
}
