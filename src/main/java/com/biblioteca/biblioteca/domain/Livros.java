package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
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
        this.quantidadeDisponivel = livrosDTO.quantidadeDisponivel();
    }

    public Livros(String isbn) {
        this.isbn = isbn;
    }
    
}

