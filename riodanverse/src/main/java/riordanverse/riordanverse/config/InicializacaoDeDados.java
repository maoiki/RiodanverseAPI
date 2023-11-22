package riordanverse.riordanverse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import riordanverse.riordanverse.services.MitologiaService;

@Component
public class InicializacaoDeDados {
    private final MitologiaService mitologiaService;

    @Autowired
    public InicializacaoDeDados(MitologiaService mitologiaService) {
        this.mitologiaService = mitologiaService;
    }

    @PostConstruct
    public void inicializarDados() {
        mitologiaService.criarMitologiasIniciais();
    }
}
