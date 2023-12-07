package riordanverse.riordanverse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Mitologia;

public interface MitologiaRepository extends JpaRepository<Mitologia, Integer>{
    Mitologia findByNome(String nome);
}
