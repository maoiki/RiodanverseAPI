package riordanverse.riordanverse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import riordanverse.riordanverse.entities.Livro;
import riordanverse.riordanverse.entities.Mitologia;
import riordanverse.riordanverse.repositories.LivroRepository;

@Service
public class LivroService {
     
     @Autowired
     private LivroRepository livroRepository;

     @Autowired
     private MitologiaService mitologiaService;

     // public Livro getLivroById(Integer)

     private void criarLivro(String nome, Integer quantidadePaginas, Integer lancamento, String mitologiaNome) {
          // Verifica se já existe um livro com esse nome
          Livro existente = livroRepository.findByNome(nome);

          if (existente != null) {
               throw new IllegalStateException("Já existe um livro com o nome: " + nome);
          }

          // Cria novo livro caso não exista algum com esse nome 
          Livro novoLivro = new Livro();
          novoLivro.setNome(nome);
          novoLivro.setQuantidadePaginas(quantidadePaginas);
          novoLivro.setLancamento(lancamento);
          
          Mitologia mitologia = mitologiaService.getMitologiaByNome(mitologiaNome);
          novoLivro.setMitologia(mitologia);

          livroRepository.save(novoLivro);
     }
     
     @Transactional
     public void criarLivrosIniciais() {
          if (livroRepository.count() == 0) {
               // Percy Jackson e Os Olimpianos:
               criarLivro("o ladrao de raios", 377, 2005, "greco-romana");
               criarLivro("o mar de monstros", 279, 2006, "greco-romana");
               criarLivro("a maldicao do tita", 312, 2007, "greco-romana");
               criarLivro("a batalha do labirinto", 361, 2008, "greco-romana");
               criarLivro("o ultimo olimpiano", 381, 2009, "greco-romana");

               // Heróis do Olimpo:
               criarLivro("o heroi perdido", 553, 2010, "greco-romana");
               criarLivro("o filho de netuno", 521, 2011, "greco-romana");
               criarLivro("a marca de atena", 586, 2012, "greco-romana");
               criarLivro("a casa de hades", 597, 2013, "greco-romana");
               criarLivro("o sangue do olimpo", 516, 2014,"greco-romana");

               // As Crônicas de Kane:
               criarLivro("a piramide vermelha", 516, 2010, "egipcia");
               criarLivro("o trono de fogo", 452, 2011, "egipcia");
               criarLivro("a sombra da serpente", 406, 2012, "egipcia");

               // Magnus Chase e os Deuses de Asgard:
               criarLivro("a espada do verao", 491, 2015, "nordica");
               criarLivro("o martelo de thor", 459, 2016, "nordica");
               criarLivro("o navio dos mortos", 410, 2017, "nordica");

               // As Provações de Apolo:
               criarLivro("o oraculo oculto", 376, 2016,"greco-romana");
               criarLivro("a profecia das trevas", 414, 2017,"greco-romana");
               criarLivro("o labirinto de fogo", 448, 2018,"greco-romana");
               criarLivro("a tumba do tirano", 448, 2019,"greco-romana");
               criarLivro("a torre de nero", 516, 2020,"greco-romana");
          }
    }

     public Livro salvar (Livro livro){
          return livroRepository.save(livro);
     }

     
}
