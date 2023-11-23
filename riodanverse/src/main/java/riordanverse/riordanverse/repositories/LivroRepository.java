package riordanverse.riordanverse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
    Livro findByNome(String nome);
}
