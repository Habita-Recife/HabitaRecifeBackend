package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_fluxo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Fluxo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFluxo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_fluxo", nullable = false)
    private TipoFluxo tipoFluxo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataFluxo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_fluxo", nullable = false)
    private Status statusFluxo;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "id_porteiro",
            foreignKey = @ForeignKey(name = "id_fluxo_porteiro_fk"))
    @JsonBackReference
    private Porteiro porteiro;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "idVisitante",
            foreignKey = @ForeignKey(name = "id_fluxo_visitante_fk"))
    @JsonBackReference
    Visitante visitante;
}
