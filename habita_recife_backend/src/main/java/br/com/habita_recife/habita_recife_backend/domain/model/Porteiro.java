package br.com.habita_recife.habita_recife_backend.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "nome_porteiro", nullable = false)
    private String nomePorteiro;

    @Column(name = "email_porteiro", nullable = false)
    private String emailPorteiro;

    @Column(name = "cpf_porteiro", nullable = false, unique = true, length = 15)
    private String cpfPorteiro;

    @OneToMany(mappedBy = "porteiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Fluxo> fluxos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_condominio",
            foreignKey = @ForeignKey(name = "id_porteiro_condominio_fk"))
    @JsonBackReference
    private Condominio condominio;

    @OneToMany(mappedBy = "porteiro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Encomenda>encomendas;




}