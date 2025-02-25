package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimeento;

    // Relacionamento com Morador (Muitos Movimentos pertencem a um Morador)
    @ManyToOne
    @JoinColumn(name = "morador_id", nullable = false,
            foreignKey = @ForeignKey(name = "morador_id"))
    private Morador morador;

    @ManyToMany
    @JoinTable(
            name = "movimento_fluxo", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "movimento_id", foreignKey = @ForeignKey(name = "fluxo_id")), // FK para Movimento
            inverseJoinColumns = @JoinColumn(name = "fluxo_id", foreignKey = @ForeignKey(name = "movimento_id")) // FK para Fluxo
    )
    private Set<Fluxo> fluxos;
}
