package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CondominioRepository extends JpaRepository<Condominio, Long> {
}
