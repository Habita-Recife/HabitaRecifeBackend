package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SindicoRepository extends JpaRepository<Sindico, Long> {
    Optional<Sindico> findByEmailSindico(String email);
    Optional<Sindico> findByNomeSindico(String nome);
    Optional<Sindico> findByCondominio(Condominio condominio);

    @Query("SELECT s FROM Sindico s LEFT JOIN FETCH s.relatorios LEFT JOIN FETCH s.servicos WHERE s.id = :id")
    Optional<Sindico> findByIdWithRelations(@Param("id") Long id);
}
