package br.com.habita_recife.habita_recife_backend.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("AVISO")
@Getter
@Setter
@NoArgsConstructor
public class SolicitacaoAviso extends Solicitacao {

    public SolicitacaoAviso(String titulo, String conteudo, Morador morador, Sindico sindico) {
        super(titulo, conteudo, morador, sindico);
    }
}
