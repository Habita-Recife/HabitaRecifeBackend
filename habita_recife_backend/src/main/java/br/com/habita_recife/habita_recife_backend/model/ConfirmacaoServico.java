package br.com.habita_recife.habita_recife_backend.model;

import br.com.habita_recife.habita_recife_backend.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_confirmacao_servico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmacaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_confirmacao_servico;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_confirmacao", nullable = false)
    private Status statusConfirmacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", nullable = false,
            foreignKey = @ForeignKey(name = "id_confirmacao_servico_empresa_fk"))
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_morador", nullable = false,
            foreignKey = @ForeignKey(name = "id_confirmacao_servico_morador_fk"))
    private Morador morador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sindico", nullable = false,
            foreignKey = @ForeignKey(name = "id_confirmacao_servico_sindico_fk"))
    private Sindico sindico;


}
