package br.com.montreal.blogpessoal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Collection;

@Entity
@Table(name = "tema")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="A descrição é obrigatória")
    private String descricao;

    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Postagem> postagens;

    public Tema() {
    }

    public Tema(Long id, String descricao, Collection<Postagem> postagens) {
        this.id = id;
        this.descricao = descricao;
        this.postagens = postagens;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(Collection<Postagem> postagens) {
        this.postagens = postagens;
    }
}
