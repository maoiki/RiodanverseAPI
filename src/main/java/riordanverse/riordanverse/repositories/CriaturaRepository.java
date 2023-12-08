package riordanverse.riordanverse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Mitologia;

public interface CriaturaRepository extends JpaRepository<Criatura, Integer> {
	Criatura findByNome(String nome);

	List<Criatura> findByMitologia(Mitologia mitologia);
}
