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

    @Column(name = "nome_condominio", nullable = false, unique = true)
    private String nomeCondominio;

    @Column(name = "numero_apartamento", nullable = false)
    private Integer numeroApartamento;

    @Column(name = "nuemro_bloco", nullable = false)
    private Integer numeroBloco;

    @Column(name = "endereco_condominio", nullable = false, unique = true, length = 300)
    private String enderecoCondominio;

    @OneToOne(mappedBy = "condominio", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Sindico sindico;

    @OneToOne(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Porteiro porteiro;

    @OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Morador> morador;
}
