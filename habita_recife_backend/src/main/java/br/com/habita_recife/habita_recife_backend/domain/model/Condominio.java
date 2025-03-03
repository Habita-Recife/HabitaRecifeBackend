package br.com.habita_recife.habita_recife_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_condominio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_condominio")
    private Long idCondominio;

    @Column(nullable = true, unique = true)
    private String nome_condominio;

    @Column(nullable = true)
    private Integer numero_apartamento;

    @Column(nullable = true)
    private Integer numero_bloco;

    @Column(nullable = true, unique = true, length = 300)
    private String endereco_condominio;

    @OneToOne(mappedBy = "condominio", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Sindico sindico;

    @OneToOne(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Porteiro porteiro;

    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Morador> morador;
}
