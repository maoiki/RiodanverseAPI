package riordanverse.riordanverse.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Acampamento;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.AcampamentoRepository;

@Service
public class AcampamentoService {
    
    @Autowired
    private AcampamentoRepository acampamentoRepository;

    @Autowired
    private MitologiaService mitologiaService;
    
    @Transactional
    public void criarAcampamentosIniciais(){
        // TODO: criar acampamento da mitologia egipcia e nordica
        
        criarAcampamento("meio-sangue", "greco-romana");
        criarAcampamento("jupiter", "greco-romana");
    }


    private void criarAcampamento(String nome, String mitologiaNome) {
        // TODO: impedir cadastro caso já exista esse acampamento, parecido como
        // ja ocorre no cadastro de mitologia
        
        Acampamento acampamento = new Acampamento();
        acampamento.setNome(nome);

        Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
        acampamento.setMitologia(mitologia);

       acampamentoRepository.save(acampamento);
    }

    public List<Acampamento> getAllAcampamentos() {
        return acampamentoRepository.findAll();
    }
    
}
