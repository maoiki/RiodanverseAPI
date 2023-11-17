package riordanverse.riordanverse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import riordanverse.riordanverse.entities.Livro;
import riordanverse.riordanverse.services.LivroService;

@RestController
@RequestMapping("/livro")
public class LivroController {
     
     @Autowired
     LivroService livroService;

     @PostMapping
     public String cadastrarLivro(@RequestBody Livro livro){
          livroService.salvar(livro);
          return "deu certo";
     }

}
