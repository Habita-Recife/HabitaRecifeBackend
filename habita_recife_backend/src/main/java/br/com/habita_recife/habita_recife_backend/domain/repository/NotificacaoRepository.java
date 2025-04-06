package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoNotificacao;
import br.com.habita_recife.habita_recife_backend.domain.model.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    Optional<Notificacao> findByIdTipoNotificacao(TipoNotificacao tipoNotificacao);
}
