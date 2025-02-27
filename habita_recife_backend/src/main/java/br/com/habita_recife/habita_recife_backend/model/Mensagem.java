package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.TipoMensagem;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_mensagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mensagem;

    @Column(nullable = false, length = 30)
    private String titulo;

    @Column(nullable = false, length = 250)
    private String conteudo;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TipoMensagem tipo_mensagem;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_mensagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "id_sindico"))
    private Sindico sindico;
}
