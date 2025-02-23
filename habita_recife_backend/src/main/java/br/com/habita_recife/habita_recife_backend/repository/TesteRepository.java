package br.com.habita_recife.habita_recife_backend.repository;

import br.com.habita_recife.habita_recife_backend.model.Prefeitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TesteRepository extends JpaRepository<Prefeitura, Long> {
}
