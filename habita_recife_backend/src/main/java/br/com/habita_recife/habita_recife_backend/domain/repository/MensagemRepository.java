package br.com.habita_recife.habita_recife_backend.domain.repository;

import br.com.habita_recife.habita_recife_backend.domain.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {    Optional<Mensagem> findByTitulo(String titulo);

    List<Mensagem> findAll();

    Optional<Mensagem> findById(Long id);

    Mensagem save(Mensagem mensagem);

    void deleteById(Long id);

    boolean existsById(Long id);
}
