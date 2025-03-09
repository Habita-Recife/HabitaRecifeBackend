package br.com.habita_recife.habita_recife_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_visitantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idVisitante;

    @Column(nullable = false, unique = true, length = 12)
    private String rgVisitante;

    @Column(nullable = false, unique = true)
    private String nomeVisitante;

    @Column(nullable = false, unique = true, length = 15)
    private String numeroVelefone;

    @OneToMany(mappedBy = "visitante",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fluxo> fluxos;

}
