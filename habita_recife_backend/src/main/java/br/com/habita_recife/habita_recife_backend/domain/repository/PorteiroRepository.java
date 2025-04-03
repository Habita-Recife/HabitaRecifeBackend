package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Porteiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PorteiroRepository extends JpaRepository<Porteiro, Long> {
    Optional<Porteiro> findByIdPorteiroOrCpfPorteiro(Long idPorteiro, String cpfPorteiro);
    Optional<Porteiro> findByCpfPorteiro(String cpfPorteiro);
    Optional<Porteiro> findByEmailPorteiro(String emailPorteiro);
}
