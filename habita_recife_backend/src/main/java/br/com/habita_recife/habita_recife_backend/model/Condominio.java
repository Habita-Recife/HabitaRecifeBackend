package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_condominio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Condominio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_condominio;

    @Column(nullable = false, unique = true)
    private String nome_condominio;

    @Column(nullable = false)
    private Integer numero_apartamento;

    @Column(nullable = false)
    private Integer numero_bloco;

    @Column(nullable = false, unique = true, length = 300)
    private String endereco_condominio;

    @OneToOne(mappedBy = "condominio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Sindico sindico;
}
