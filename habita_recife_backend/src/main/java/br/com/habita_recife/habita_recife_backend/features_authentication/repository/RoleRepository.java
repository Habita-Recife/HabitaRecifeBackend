package br.com.habita_recife.habita_recife_backend.features_authentication.repository;

import br.com.habita_recife.habita_recife_backend.features_authentication.model.Role;
import br.com.habita_recife.habita_recife_backend.features_authentication.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(RoleName role);
}
