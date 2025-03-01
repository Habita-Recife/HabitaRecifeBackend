package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_porteiro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Porteiro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_porteiro;

    @Column(nullable = false, unique = true)
    private String nome_porteiro;

    @Column(nullable = false, unique = true, length = 15)
    private String cpf_porteiro;

    @OneToMany(mappedBy = "porteiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fluxo> fluxos = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Condominio condominio;
}
