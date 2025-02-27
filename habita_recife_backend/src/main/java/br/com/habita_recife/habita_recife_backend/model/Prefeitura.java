package br.com.habita_recife.habita_recife_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
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
    private Long id_prefeitura;

    @OneToOne(mappedBy = "prefeitura", fetch = FetchType.LAZY)
    private Relatorio relatorio;
}
