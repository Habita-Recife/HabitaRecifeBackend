package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.Status;
import br.com.habita_recife.habita_recife_backend.enums.TipoFluxo;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tb_fluxo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fluxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_fluxo;

    @Enumerated(EnumType.STRING)
    private TipoFluxo tipo_fluxo;

    @Enumerated(EnumType.STRING)
    private Status status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_fluxo;

    @ManyToMany(mappedBy = "fluxos")
    private Set<Movimento> movimentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fluxo_id", nullable = false,
            foreignKey = @ForeignKey(name = "fluxo_id"))
    private Porteiro porteiro;

    /*@ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "porteiro_id", nullable = false ,
            foreignKey = @ForeignKey(name = "porteiro_id"))
    private Porteiro porteiro;*/
}
