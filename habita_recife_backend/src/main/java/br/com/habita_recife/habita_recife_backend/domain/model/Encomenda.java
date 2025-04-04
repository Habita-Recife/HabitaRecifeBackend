package br.com.habita_recife.habita_recife_backend.domain.model;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoEncomenda;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_encomenda")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Encomenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_encomenda;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEncomenda tipoEncomenda;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataEncomenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encomenda" ,nullable = false,
            foreignKey = @ForeignKey(name="id_encomenda_porteiro_fk"))
    private Porteiro porteiro;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_encomenda", nullable = false,
            foreignKey = @ForeignKey(name = "id_encomenda_morador_fk"))
    private Morador morador;



}
