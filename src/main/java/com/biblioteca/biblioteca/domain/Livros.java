package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "livro")
public class Livros {

    @Id
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "paginas")
    private String paginas;

    // Outros atributos do livro
    @JsonIgnore
    @OneToMany(mappedBy = "livro")
    private Set<LivroAutor> autores;

    public Livros(LivrosDTO livrosDTO){
        this.isbn = livrosDTO.isbn();
        this.paginas = livrosDTO.n_pagina();
        this.titulo = livrosDTO.titulo();
    }

    public Livros(){

    }

    public Livros(String isbn) {
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
    }

    public Set<LivroAutor> getAutores() {
        return autores;
    }

    public void setAutores(Set<LivroAutor> autores) {
        this.autores = autores;
    }

    
}

