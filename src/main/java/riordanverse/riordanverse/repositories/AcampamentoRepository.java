package riordanverse.riordanverse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.entities.Mitologia;

public interface AcampamentoRepository extends JpaRepository<Acampamento, Integer> {
	Acampamento findByNome(String nome);

	List<Acampamento> findByMitologia(Mitologia mitologia);
}
