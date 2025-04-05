package br.com.montreal.blogpessoal.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String usuario;
    private String senha;
    private String foto;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Postagem> postagens;

    public Usuario(Long id, String nome, String usuario, String senha, String foto, Collection<Postagem> postagens) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.foto = foto;
        this.postagens = postagens;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Collection<Postagem> getPostagens() {
        return postagens;
    }

    public void setPostagens(Collection<Postagem> postagens) {
        this.postagens = postagens;
    }
}

