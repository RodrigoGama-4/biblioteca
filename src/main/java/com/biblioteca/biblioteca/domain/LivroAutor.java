package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "livro_autor")
public class LivroAutor {

    @Id
    @Column(name = "livro_autor_id", columnDefinition = "BINARY(16)")
    private UUID livroAutorId;

    @ManyToOne
    @JoinColumn(name = "livro_isbn", referencedColumnName = "isbn")
    private Livros livro;

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "autor_id")
    private Autor autor;


}
