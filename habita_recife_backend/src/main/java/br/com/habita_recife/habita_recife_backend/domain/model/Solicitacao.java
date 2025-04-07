package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoSolicitacao;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_solicitacao")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_solicitacao", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitacao;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 250)
    private String conteudo;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_solicitacao", nullable = false)
    private Status status_solicitacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "id_solicitacao_morador_fk"))
    @JsonBackReference
    private Morador morador;

    @ManyToOne
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "id_solicitacao_sindico_fk"))
    @JsonBackReference
    private Sindico sindico;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    public Solicitacao(String titulo, String conteudo, Morador morador, Sindico sindico) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.morador = morador;
        this.sindico = sindico;
        this.status_solicitacao = Status.PENDENTE;
    }

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}