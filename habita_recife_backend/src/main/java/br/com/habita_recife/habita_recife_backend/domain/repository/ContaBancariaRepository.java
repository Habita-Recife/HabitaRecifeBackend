package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.ContaBancaria;
import br.com.habita_recife.habita_recife_backend.domain.model.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    Optional<ContaBancaria> findBy(String nomeEmpresa);
    Optional<ContaBancaria> findByFinanceiro(Financeiro financeiro);


}
