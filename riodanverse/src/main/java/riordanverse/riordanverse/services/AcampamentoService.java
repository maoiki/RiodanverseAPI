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
    
    private void criarAcampamento(String nome, String mitologiaNome) {
       
        Acampamento existente = acampamentoRepository.findByNome(nome);
        if(existente != null){
            throw new IllegalStateException("Já existe um acampamento com o nome: " + nome);
        }

        Acampamento acampamento = new Acampamento();
        acampamento.setNome(nome);

        Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
        acampamento.setMitologia(mitologia);

       acampamentoRepository.save(acampamento);
    }

    @Transactional
    public void criarAcampamentosIniciais(){
       
        if(acampamentoRepository.count() == 0){
            criarAcampamento("meio-sangue", "greco-romana");
            criarAcampamento("jupiter", "greco-romana");
            criarAcampamento("casa da vida", "egipcia");
            criarAcampamento("hotel vahalla", "nordica");
        }
    }

    public List<Acampamento> getAllAcampamentos() {
        return acampamentoRepository.findAll();
    }

    public Acampamento salvar (Acampamento acampamento){
        return acampamentoRepository.save(acampamento);
    }

   public Acampamento atualizar(Acampamento acampamento){
        return acampamentoRepository.save(acampamento);
    }

   public void remover(Integer idAcampamento){
        acampamentoRepository.deleteById(idAcampamento);
    }
    
}
