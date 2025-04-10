package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Condominio;
import br.com.habita_recife.habita_recife_backend.domain.model.Sindico;
import br.com.habita_recife.habita_recife_backend.domain.model.Vitrine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VitrineRepository extends JpaRepository<Vitrine, Long> {

    @Query("SELECT v FROM Vitrine v WHERE v.morador.condominio.idCondominio = :idCondominio")
    List<Vitrine> findByCondominioId(@Param("idCondominio") Long idCondominio);
    Optional<Vitrine> findBySindico(Sindico sindico);
    Optional<Vitrine> findByNomeProduto(String nome_produto);
}
