package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoSolicitacao;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_solicitacao")
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoSolicitacao tipo_solicitacao;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_solicitacao", nullable = false)
    private Status status_solicitacao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "id_solicitacao_morador_fk"))
    private Morador morador;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Solicitacao(String titulo, String conteudo, TipoSolicitacao tipo_solicitacao, Morador morador) {
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.tipo_solicitacao = tipo_solicitacao;
        this.status_solicitacao = Status.PENDENTE;
        this.morador = morador;
    }
}
