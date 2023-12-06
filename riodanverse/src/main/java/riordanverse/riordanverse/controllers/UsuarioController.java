package riordanverse.riordanverse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import riordanverse.riordanverse.entities.Usuario;
import riordanverse.riordanverse.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/all")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
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

    @GetMapping("/perfil")
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN","ROLE_CAMPISTA"})
    public Usuario getUsuarioPerfil(Authentication authentication){
        try {
            Usuario usuario = usuarioService.getUsuarioByAuthentication(authentication);
            return usuario;
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao obter perfil do usuário", e);
        }
    }


    @PostMapping
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public String cadastrarUsuario(@RequestBody Usuario usuario,  Authentication authentication){
        try {
            usuarioService.salvar(usuario, authentication);
    
            String login = usuario.getLogin();
            return "Usuario " + login + " cadastrado com sucesso!";
        } catch (RuntimeException e) {
            return "Erro ao cadastrar usuário: " + e.getMessage();
        }
    }

    @PutMapping
    @Secured(value = {"ROLE_FUNCIONARIO","ROLE_ADMIN"})
    public String atualizarUsuario(@RequestBody Usuario usuario,  Authentication authentication){
        try {
            usuarioService.atualizar(usuario, authentication);
    
            String login = usuario.getLogin();
            return "Usuario " + login + " atualizado com sucesso!";
        } catch (RuntimeException e) {
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

   

    @DeleteMapping("/id/{idUser}")
    @Secured(value = {"ROLE_ADMIN"})
    public String removerUser(@PathVariable Integer idUser,  Authentication authentication){
        try {
            Usuario existente = usuarioService.getUsuarioById(idUser);
            usuarioService.remover(idUser, authentication);
    
            return "Usuario " + existente.getLogin() +" removido com sucesso!";
        } catch (RuntimeException e) {
            return "Erro ao deletar usuário: " + e.getMessage();
        }
    }
}
