package riordanverse.riordanverse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Livro;
import riordanverse.riordanverse.entities.Mitologia;

public interface LivroRepository extends JpaRepository<Livro, Integer>{
    Livro findByNome(String nome);
    List<Livro> findByQuantidadePaginas(Integer quantidadePaginas);
    List<Livro> findByLancamento(Integer ano);
    List<Livro> findByMitologia(Mitologia mitologia);
}
