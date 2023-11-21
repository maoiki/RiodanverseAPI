package riordanverse.riordanverse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.MitologiaRepository;

@Service
public class MitologiaService {
     
     @Autowired
     private MitologiaRepository mitologiaRepository;

     public Mitologia getMitologia (Integer idMitologia){
          Optional<Mitologia> mitologia = mitologiaRepository.findById(idMitologia);
          return mitologia.get();
     }
}
