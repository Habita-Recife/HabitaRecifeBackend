package br.com.habita_recife.habita_recife_backend.domain.repository;


import br.com.habita_recife.habita_recife_backend.domain.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Optional<Servico> findById(Long id_servico);

}
