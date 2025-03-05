package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Long> {
    Optional<Condominio> findByNomeCondominioOrId(String nomeCondominio, Long id);
}
