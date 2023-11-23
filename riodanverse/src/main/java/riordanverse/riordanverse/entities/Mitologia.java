package riordanverse.riordanverse.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Mitologia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    // OBS: esse @JsonManagedReference em conjunto com o @JsonBackReference dos "filhos"
    // impede de ficar fazendo referencias ciclicas, que estavam gerando erros

    // Uma mitologia está associada a diversos acampamentos
    @OneToMany(mappedBy = "mitologia")
    @JsonManagedReference
    private List<Acampamento> acampamentos;

    // Uma mitologia está associada a diversos livros
    @OneToMany(mappedBy = "mitologia")
    @JsonManagedReference
    private List<Livro> livros;
}
