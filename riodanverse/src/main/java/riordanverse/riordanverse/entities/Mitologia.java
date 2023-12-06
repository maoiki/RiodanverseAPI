package riordanverse.riordanverse.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    // Uma mitologia está associada a diversos acampamentos
    @OneToMany(mappedBy = "mitologia")
    @JsonIgnore
    private List<Acampamento> acampamentos;

    // Uma mitologia está associada a diversos livros
    @OneToMany(mappedBy = "mitologia")
    @JsonIgnore
    private List<Livro> livros;

    @OneToMany(mappedBy = "mitologia")
    @JsonIgnore
    private List<Criatura> criaturas;
}
