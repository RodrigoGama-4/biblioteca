package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.biblioteca.biblioteca.dtos.LivrosDTO;

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
    @OneToMany(mappedBy = "livro")
    private Set<LivroAutor> autores;

    public Livros(LivrosDTO livrosDTO){
        this.isbn = livrosDTO.isbn();
        this.paginas = livrosDTO.n_pagina();
        this.titulo = livrosDTO.titulo();
    }

    public Livros(){

    }
}

