package riordanverse.riordanverse.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Acampamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;

    // Um acampamento está associado a uma única mitologia
    @ManyToOne
    @JoinColumn(name = "mitologia_id")
    @JsonBackReference
    private Mitologia mitologia;

    @OneToMany(mappedBy = "acampamento")
    @JsonIgnore
    private List<Usuario> usuarios;
}
