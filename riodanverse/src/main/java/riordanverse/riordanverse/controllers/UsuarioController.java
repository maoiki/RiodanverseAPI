package riordanverse.riordanverse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import riordanverse.riordanverse.entities.Usuario;
import riordanverse.riordanverse.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/all")
    public List<Usuario> getAllUsuarios(){
        List <Usuario> usuarios = usuarioService.getAllUsuarios();
        return usuarios;
    }

    @GetMapping("/id/{idUsuario}")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public Usuario getUsuarioById(@PathVariable Integer idUsuario){
        Usuario usuario = usuarioService.getUsuarioById(idUsuario);
        return usuario;
    }

    @GetMapping("/login/{nomeLogin}")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public Usuario getUsuarioByLogin(@PathVariable String nomeLogin){
        Usuario usuario = usuarioService.getUsuarioByLogin(nomeLogin);
        return usuario;
    }

    @GetMapping("/criatura/{nomeCriatura}")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public List<Usuario> getUsuarioByCriatura(@PathVariable String nomeCriatura){
        List<Usuario> usuarios = usuarioService.getUsuarioByCriatura(nomeCriatura);

        if(usuarios.isEmpty()){
            throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
        }

        return usuarios;
    }

    @GetMapping("/acamp/{acampNome}")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public List<Usuario> getUsuarioByAcampamento(@PathVariable String acampNome){
        List<Usuario> usuarios = usuarioService.getUsuarioByAcampamento(acampNome);
        
        if(usuarios.isEmpty()){
            throw new IllegalStateException("Não foram encontrados resultados para essa busca!");
        }

        return usuarios;
    }


    @PostMapping
    public String cadastrarUsuario(@RequestBody Usuario usuario){
        String login = usuario.getLogin();
        Usuario existente = usuarioService.getUsuarioByLogin(login);

        if(existente != null){
            throw new IllegalStateException("Já existe um usuario com o login: " + login); 
        }

        usuarioService.salvar(usuario);

        String feedback = "Usuario "+login+" cadastrado com sucesso!";
        return feedback;

    }

    @PutMapping
    public String atualizarUsuario(@RequestBody Usuario usuario){
        String login = usuario.getLogin();
        Usuario existente = usuarioService.getUsuarioByLogin(login);

        if(existente != null){
            throw new IllegalStateException("Já existe um usuario com o login: " + login); 
        }

         usuarioService.salvar(usuario);

        String feedback = "Usuario "+login+" cadastrado com sucesso!";
        return feedback;
    }

    @DeleteMapping("/id/{idUser}")
    public String removerUser(@PathVariable Integer idUser){
        Usuario existente = usuarioService.getUsuarioById(idUser);
        
        usuarioService.remover(idUser);
        String feedback = "Usuario " + existente.getLogin() +" removido com sucesso!";
          return feedback;
    }
}
