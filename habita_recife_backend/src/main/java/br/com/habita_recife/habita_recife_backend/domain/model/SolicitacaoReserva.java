package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoReserva;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("RESERVA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoReserva extends Solicitacao {

    private LocalDateTime dataReserva;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoReserva tipoReserva;

    public SolicitacaoReserva(String titulo, String conteudo, Morador morador
            , LocalDateTime dataReserva, TipoReserva tipoReserva, Sindico sindico) {
        super(titulo, conteudo, morador, sindico);
        this.dataReserva = dataReserva;
        this.tipoReserva = tipoReserva;
    }
}