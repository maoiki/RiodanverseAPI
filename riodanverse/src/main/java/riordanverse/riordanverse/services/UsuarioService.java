package riordanverse.riordanverse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Usuario;
import riordanverse.riordanverse.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CriaturaService criaturaService;

    @Autowired
    private AcampamentoService acampamentoService;


    public Usuario getUsuarioByLogin(String login){
     return usuarioRepository.findByLogin(login);
    }

    public Usuario getUsuarioById(Integer idUser){
     Optional<Usuario> usuario = usuarioRepository.findById(idUser);
     return usuario.get();
    }

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

     public List<Usuario> getUsuarioByCriatura(String criaturaNome){
          Criatura criatura = criaturaService.getCriaturaByNome(criaturaNome);
          return usuarioRepository.findByCriatura(criatura);
    }

    public List<Usuario> getUsuarioByAcampamento(String acampNome){
          Acampamento acamp = acampamentoService.getAcampamentoByNome(acampNome);
          return usuarioRepository.findByAcampamento(acamp);
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
