package br.com.habita_recife.habita_recife_backend.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("POSTAGEM_VITRINE")
@Getter
@Setter
@NoArgsConstructor
public class SolicitacaoVitrine extends Solicitacao {

    @OneToOne
    private Vitrine vitrine;

    public SolicitacaoVitrine(String titulo, String conteudo, Morador morador, Vitrine vitrine, Sindico sindico) {
        super(titulo, conteudo, morador, sindico);
        this.vitrine = vitrine;
    }
}