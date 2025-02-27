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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fluxo", nullable = false,
            foreignKey = @ForeignKey(name = "id_fluxo"))
    private Porteiro porteiro;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_porteiro", nullable = false ,
            foreignKey = @ForeignKey(name = "id_porteiro"))
    private Porteiro porteiros;
}
