package br.com.habita_recife.habita_recife_backend.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_prefeitura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prefeitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrefeitura;

    @Column(name = "nome_prefeitura", nullable = false)
    private String nomePrefeitura;

    @Column(name = "email_prefeitura")
    private String emailPrefeitura;

    @OneToOne(mappedBy = "prefeitura", fetch = FetchType.LAZY)
    private Relatorio relatorio;
}
