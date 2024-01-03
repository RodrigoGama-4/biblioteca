package com.biblioteca.biblioteca.domain;

import com.biblioteca.biblioteca.dtos.LivrosAutorDTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public LivroAutor(LivrosAutorDTO livrosAutorDTO){
        this.livroAutorId = livrosAutorDTO.id();
        this.autor = livrosAutorDTO.autorId();
        this.livro = livrosAutorDTO.isbn();
    }

    public LivroAutor(){

    }
    
}
