package riordanverse.riordanverse.services;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Usuario;
import riordanverse.riordanverse.enums.Funcao;
import riordanverse.riordanverse.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	// REGEX simples para ter entre 6 e 20 digitos de senha
	private static final String PASSWORD_REGEX = "^.{6,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CriaturaService criaturaService;

    @Autowired
    private AcampamentoService acampamentoService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean validarSenha(String senha){
		Matcher matcher = pattern.matcher(senha);
		return matcher.matches();
	}

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

    public Usuario salvar (Usuario usuario, Authentication authentication) throws RuntimeException {
    	String login = usuario.getLogin();
    	Usuario existente = usuarioRepository.findByLogin(login);

		if(existente != null){
			throw new RuntimeException("Já existe um usuario com o login: " + login); 
		}
		
		Funcao funcao = usuario.getFuncao();
		Boolean isFuncaoRestrita = (funcao == Funcao.ROLE_FUNCIONARIO) || (funcao == Funcao.ROLE_ADMIN);
		
		// Lança erro se não está autenticado ou se quer cadastrar uma função restrita sem ser admin
		if (authentication == null ||
		!authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"))  && 
		isFuncaoRestrita) {
			throw new AccessDeniedException("Você não tem permissão para cadastrar um usuário com a função " + funcao.name());
		}

		String senhaNormal = usuario.getSenha();
		String senhaCriptografada = bCryptPasswordEncoder.encode(senhaNormal);
		usuario.setSenha(senhaCriptografada);
		
		Matcher matcher = pattern.matcher(senhaNormal);
		if (!matcher.matches()){
			throw new RuntimeException("A senha deve possuir entre 6 e 20 caracteres.");
		}
		
        return usuarioRepository.save(usuario);
     }

     public Usuario atualizar(Usuario usuario, Authentication authentication){
		Boolean isCampista = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_CAMPISTA"));
		Boolean isAdmin = authentication.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

		Funcao funcao = usuario.getFuncao();
		Boolean isFuncaoRestrita = (funcao == Funcao.ROLE_FUNCIONARIO) || (funcao == Funcao.ROLE_ADMIN);
		
		Usuario usuarioAutenticado = getUsuarioByLogin(authentication.getName());
		Usuario usuarioExistente = getUsuarioByLogin(usuario.getLogin());
		Boolean isMesmoUsuario = (usuarioAutenticado == usuarioExistente);

		String senha = usuario.getSenha();
		Boolean isSenhaValida = validarSenha(senha);

		if (usuario.getId() == null){
			throw new RuntimeException("É necessário informar o id do usuário a ser atualizado.");
		}

		if (authentication == null || (isCampista && !isMesmoUsuario) ) {
        	throw new AccessDeniedException("Você não tem permissão para atualizar este usuário.");
    	}

		if (usuarioExistente != null && !isMesmoUsuario)  {
        	throw new RuntimeException("Já existe um usuário com este login.");
    	}

		if (!isAdmin && isFuncaoRestrita) {
        	throw new AccessDeniedException("Você não tem permissão para atualizar a função deste usuário.");
    	}
		
		if(isSenhaValida){
			String senhaCriptografada = bCryptPasswordEncoder.encode(senha);
			usuario.setSenha(senhaCriptografada);
		} else {
			throw new RuntimeException("A senha deve possuir entre 6 e 20 caracteres.");
		}

        return usuarioRepository.save(usuario);
     }

     public void remover(Integer idUsuario){
          usuarioRepository.deleteById(idUsuario);
     }
    
}
