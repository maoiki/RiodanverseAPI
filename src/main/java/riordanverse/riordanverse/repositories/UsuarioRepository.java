package riordanverse.riordanverse.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByLogin(String login);
    List<Usuario> findByFuncao(String funcao);
    List<Usuario> findByCriatura(Criatura criatura);
    List<Usuario> findByAcampamento(Acampamento acampamento);
}
