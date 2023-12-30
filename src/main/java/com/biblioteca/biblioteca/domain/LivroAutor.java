package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "livro_autor")
public class LivroAutor {

    @Id
    @Column(name = "livro_autor_id", columnDefinition = "BINARY(16)")
    private Long livroAutorId;

    @ManyToOne
    @JoinColumn(name = "livro_isbn", referencedColumnName = "isbn")
    private Livros livro;

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id")
    private Autor autor;


}
