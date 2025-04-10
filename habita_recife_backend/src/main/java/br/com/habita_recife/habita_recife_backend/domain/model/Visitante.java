package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "cpf_visitante", nullable = false, unique = true, length =
            14)
    private String cpfVisitante;

    @Column(nullable = false)
    private String nomeVisitante;

    @Column(nullable = false, unique = true, length = 15)
    private String numeroTelefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_visitante", nullable = false)
    private Status statusVisitante;

    @OneToMany(mappedBy = "visitante",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Fluxo> fluxos;

}
