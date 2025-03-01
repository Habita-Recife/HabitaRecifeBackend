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
    @Column(name = "tipo_fluxo", nullable = false)
    private TipoFluxo tipoFluxo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_fluxo", nullable = false)
    private Status statusFluxo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime data_fluxo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "id_fluxo_morador_fk"))
    private Morador morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_visitante", nullable = false,
            foreignKey = @ForeignKey(name = "id_fluxo_visitante_fk"))
    private Visitante visitante;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_porteiro", nullable = false ,
            foreignKey = @ForeignKey(name = "id_fluxo_porteiro_fk"))
    private Porteiro porteiro;
}
