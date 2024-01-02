package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.biblioteca.biblioteca.dtos.AutorDTO;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue()
    @Column(name = "autor_id")
    private Long autorId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;


    @OneToMany(mappedBy = "autor")
    private Set<LivroAutor> livros;

    public Autor(AutorDTO autorDTO){
        this.nome = autorDTO.nome();
        this.nacionalidade = autorDTO.nacionalidade();
        this.nascimento = autorDTO.dt_nasci();
    }

    public Autor() {
    }

    public Autor(Long autorId) {
        this.autorId = autorId;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Set<LivroAutor> getLivros() {
        return livros;
    }

    public void setLivros(Set<LivroAutor> livros) {
        this.livros = livros;
    }

    
}
