package br.com.habita_recife.habita_recife_backend.domain.dto;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoMensagem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDTO {

    private Long idMensagem;
    private Long idSindico;
    private String titulo;
    private String conteudo;
    private TipoMensagem tipoMensagem;
    private LocalDateTime dataMensagem;

}
