package riordanverse.riordanverse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import riordanverse.riordanverse.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
