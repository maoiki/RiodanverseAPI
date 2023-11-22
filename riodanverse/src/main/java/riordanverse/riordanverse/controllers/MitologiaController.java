package riordanverse.riordanverse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.services.MitologiaService;

@RestController
@RequestMapping("/mitologia")
public class MitologiaController {
     
     @Autowired
     MitologiaService mitologiaService;

     @GetMapping("{idMitologia}")
     public Mitologia getMitologia(@PathVariable Integer idMitologia){
          Mitologia mitologia = mitologiaService.getMitologia(idMitologia);
          return mitologia;
     }

     @GetMapping("/nome/{nomeMitologia}")
     public Mitologia getMitologiaByNome(@PathVariable String nomeMitologia){
          Mitologia mitologia = mitologiaService.getMitologiaByNome(nomeMitologia);
          return mitologia;
     }

     @GetMapping("/all")
     public List<Mitologia> getAllMitologias(){
          List<Mitologia> mitologias = mitologiaService.getAllMitologias();
          return mitologias;
     }
}
