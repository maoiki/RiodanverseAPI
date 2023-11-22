package riordanverse.riordanverse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.MitologiaRepository;

@Service
public class MitologiaService {
     
     @Autowired
     private MitologiaRepository mitologiaRepository;

     private void criarMitologia(String nome) {
         Mitologia mitologia = new Mitologia();
         mitologia.setNome(nome);
 
         mitologiaRepository.save(mitologia);
     }
     
     @Transactional
     public void criarMitologiasIniciais() {
        criarMitologia("greco-romana");
        criarMitologia("nordica");
        criarMitologia("egipcia");
    }


     public Mitologia getMitologia (Integer idMitologia){
          Optional<Mitologia> mitologia = mitologiaRepository.findById(idMitologia);
          return mitologia.get();
     }
}
