package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VitrineRepository extends JpaRepository<Vitrine, Long> {

    Optional<Vitrine> findByNomeProduto(String nome_produto);
}
