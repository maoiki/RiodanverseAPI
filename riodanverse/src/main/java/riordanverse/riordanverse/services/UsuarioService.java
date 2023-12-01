package riordanverse.riordanverse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import riordanverse.riordanverse.entities.Usuario;
import riordanverse.riordanverse.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUsuarioByLogin(String login){
     return usuarioRepository.findByLogin(login);
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario salvar (Usuario usuario){
          return usuarioRepository.save(usuario);
     }

     public Usuario atualizar(Usuario usuario){
          return usuarioRepository.save(usuario);
     }

     public void remover(Integer idUsuario){
          usuarioRepository.deleteById(idUsuario);
     }
    
}
