package br.com.habita_recife.habita_recife_backend.features_authentication.repository;

import br.com.habita_recife.habita_recife_backend.features_authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
