package br.com.habita_recife.habita_recife_backend.domain.repository;
import br.com.habita_recife.habita_recife_backend.domain.model.ConfirmacaoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ConfirmacaoServicoRepository extends JpaRepository<ConfirmacaoServico, Long> {


}