package riordanverse.riordanverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import riordanverse.riordanverse.entities.Livro;
import riordanverse.riordanverse.repositories.LivroRepository;

@Service
public class LivroService {
     
     @Autowired
     private LivroRepository livroRepository;

     // public Livro getLivroById(Integer)

     public Livro salvar (Livro livro){
          return livroRepository.save(livro);
     }
}
