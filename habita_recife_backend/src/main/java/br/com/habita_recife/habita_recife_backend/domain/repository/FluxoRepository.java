package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Fluxo;
import br.com.habita_recife.habita_recife_backend.domain.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FluxoRepository extends JpaRepository<Fluxo, Long> {
    Optional<Fluxo> findByVisitante(Visitante visitante);
}