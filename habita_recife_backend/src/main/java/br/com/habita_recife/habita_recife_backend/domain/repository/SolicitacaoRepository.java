package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.morador.id = :moradorId AND s.dataCriacao >= :horaAtras")
    long countSolicitacoesUltimaHora(@Param("moradorId") Long moradorId, @Param("horaAtras") LocalDateTime horaAtras);

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.morador.id = :moradorId AND s.dataCriacao >= :diaAtras")
    long countSolicitacoesUltimoDia(@Param("moradorId") Long moradorId, @Param("diaAtras") LocalDateTime diaAtras);

    Optional<Solicitacao> findByTitulo(String titulo);
}
