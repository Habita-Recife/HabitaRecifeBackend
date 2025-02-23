package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    private Long sindico_id;

    @Column(nullable = false, unique = true)
    private String nome_sindico;

    @Column(nullable = false, length = 15)
    private String telefone_sindico;

    @Column(nullable = false, length = 10, unique = true)
    private String rg_sindico;
    
    @OneToMany(mappedBy = "sindico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Relatorio> relatorios = new HashSet<>();



}
