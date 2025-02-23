package br.com.habita_recife.habita_recife_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "tb_cobranca")
public class Cobranca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cobranca_id;

    @Column(nullable = false)
    private Double valor_cobranca;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_cobranca;

//    @ManyToOne
//        @JoinColumn(name = "morador_id", nullable = false)
//    private Morador morador;
//
//    @ManyToOne
//        @JoinColumn(name = "sindico_id", nullable = false)
//    private Sindico sindico;
}
