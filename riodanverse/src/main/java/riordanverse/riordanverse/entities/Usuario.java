package riordanverse.riordanverse.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
     private String login;
     
    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String funcao;

    @ManyToOne
    @JoinColumn(name = "criatura_id")
    @JsonIgnore
    private Criatura criatura;

    @ManyToOne
    @JoinColumn(name = "acampamento_id")
    @JsonIgnore
    private Acampamento acampamento;
}
