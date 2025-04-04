package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Prefeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrefeituraRepository extends JpaRepository<Prefeitura, Long>{
    Optional<Prefeitura> findByNomePrefeitura(String nomePrefeitura);
    Optional<Prefeitura> findByEmailPrefeitura(String emailPrefeitura);
}
