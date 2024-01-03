package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
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

    @Column(name = "quantidade_disponivel")
    private int quantidadeDisponivel;

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
    
}

