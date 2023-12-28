package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;

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

}

