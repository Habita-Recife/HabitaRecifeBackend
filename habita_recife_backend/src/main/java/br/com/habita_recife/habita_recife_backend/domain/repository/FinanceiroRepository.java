package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.enums.TipoCobranca;
import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceiroRepository extends JpaRepository<Financeiro,Long> {

    Optional<Financeiro>findByTipoCobranca(TipoCobranca tipoCobranca );

}
