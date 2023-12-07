package riordanverse.riordanverse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import riordanverse.riordanverse.services.AcampamentoService;
import riordanverse.riordanverse.services.CriaturaService;
import riordanverse.riordanverse.services.LivroService;
import riordanverse.riordanverse.services.MitologiaService;

@Component
public class InicializacaoDeDados {
	private final MitologiaService mitologiaService;
	private final AcampamentoService acampamentoService;
	private final LivroService livroService;
	private final CriaturaService criaturaService;

	@Autowired
	public InicializacaoDeDados(MitologiaService mitologiaService, AcampamentoService acampamentoService, LivroService livroService, CriaturaService criaturaService) {
		this.mitologiaService = mitologiaService;
		this.acampamentoService = acampamentoService;
		this.livroService = livroService;
		this.criaturaService = criaturaService;
	}


	@PostConstruct
	public void inicializarDados() {
		mitologiaService.criarMitologiasIniciais();
		acampamentoService.criarAcampamentosIniciais();
		livroService.criarLivrosIniciais();
		criaturaService.criarCriaturasIniciais();
	}
}
