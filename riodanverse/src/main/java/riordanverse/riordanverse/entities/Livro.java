package riordanverse.riordanverse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Livro {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @Column(nullable = false)
     private String nome;

     @Column(nullable = false)
     private Integer quantidadePaginas;

     @Column(nullable = false)
     private Integer lancamento;

     @Column(nullable = false)
     private String mitologia;
}
