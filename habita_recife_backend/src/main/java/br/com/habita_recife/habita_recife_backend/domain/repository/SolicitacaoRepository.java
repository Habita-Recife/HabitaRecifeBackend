package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {//notação losango

    Optional<Solicitacao> findByTitulo(String titulo);

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.morador.id = moradorId AND s.dataCriacao >= :umaHoraAtras")
    long countSolicitacoesUltimahora(@Param("moradorId") long moradorId, @Param("umaHoraAtras") LocalDateTime umDiaAtras);

    @Query("SELECT COUNT(s) FROM Solicitacao s WHERE s.morador.id = :moradorId AND s.dataCriacao >= umDiaAtras")
    long countSolicitacoersUltimoDia(@Param("moradorId") long mordaroId, @Param("umDiaAtras") LocalDateTime umDiaAtras);

}
