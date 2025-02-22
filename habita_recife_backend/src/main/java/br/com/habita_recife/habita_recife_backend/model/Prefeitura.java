package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_prefeitura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomePrefeitura;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Set<Relatorio> relatorios;
}
