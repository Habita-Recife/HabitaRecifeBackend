package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.Status;
import br.com.habita_recife.habita_recife_backend.domain.enums.TipoFluxo;
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
