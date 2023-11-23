package riordanverse.riordanverse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import riordanverse.riordanverse.services.AcampamentoService;
import riordanverse.riordanverse.services.LivroService;
import riordanverse.riordanverse.services.MitologiaService;

@Component
public class InicializacaoDeDados {
    private final MitologiaService mitologiaService;
    private final AcampamentoService acampamentoService;
    private final LivroService livroService;

    @Autowired
    public InicializacaoDeDados(MitologiaService mitologiaService, AcampamentoService acampamentoService, LivroService livroService) {
        this.mitologiaService = mitologiaService;
        this.acampamentoService = acampamentoService;
        this.livroService = livroService;
    }

    

    @PostConstruct
    public void inicializarDados() {
        mitologiaService.criarMitologiasIniciais();
        acampamentoService.criarAcampamentosIniciais();
        livroService.criarLivrosIniciais();
    }
}
