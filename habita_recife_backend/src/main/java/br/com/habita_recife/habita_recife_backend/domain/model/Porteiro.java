package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long idPorteiro;

    @Column(name = "nome_porteiro", nullable = false, unique = true)
    private String nomePorteiro;

    @Column(name = "cpf_porteiro", nullable = false, unique = true, length = 15)
    private String cpfPorteiro;

    @OneToMany(mappedBy = "porteiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Fluxo> fluxos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio", nullable = true,
            foreignKey = @ForeignKey(name = "id_porteiro_condominio_fk"))
    @JsonBackReference
    private Condominio condominio;
}