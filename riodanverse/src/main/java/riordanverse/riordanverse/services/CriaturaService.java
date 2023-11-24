package riordanverse.riordanverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Criatura;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.CriaturaRepository;

@Service
public class CriaturaService {
     
     @Autowired
     private CriaturaRepository criaturaRepository;

     @Autowired
     private MitologiaService mitologiaService;

     private void criarCriatura(String nome, String mitologiaNome){
          //verifica se já existe uma criatura com esse nome
          Criatura existente = criaturaRepository.findByNome(nome);

          if(existente != null){
               throw new IllegalStateException("Já existe uma criatura com o nome: " + nome);
          }

          //Cria nova criatura caso não exista algum com esse nome
          Criatura novaCriatura = new Criatura();
          novaCriatura.setNome(nome);
          
          Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
          novaCriatura.setMitologia(mitologia);

          criaturaRepository.save(novaCriatura);
     }

     @Transactional
     public void criarCriaturasIniciais(){
          if(criaturaRepository.count()==0){
               //Greco-romana
               criarCriatura("deus greco-romano", "greco-romana");
               criarCriatura("semideus", "greco-romana");
               criarCriatura("medusa", "greco-romana");


          }
     }

}
