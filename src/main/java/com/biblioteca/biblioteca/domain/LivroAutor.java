package com.biblioteca.biblioteca.domain;

import com.biblioteca.biblioteca.dtos.LivrosAutorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    public LivroAutor(LivrosAutorDTO livrosAutorDTO){
        this.livroAutorId = livrosAutorDTO.id();
        this.autor = livrosAutorDTO.autorId();
        this.livro = livrosAutorDTO.isbn();
    }

    public LivroAutor(){

    }

    public Long getLivroAutorId() {
        return livroAutorId;
    }

    public void setLivroAutorId(Long livroAutorId) {
        this.livroAutorId = livroAutorId;
    }

    public Livros getLivro() {
        return livro;
    }

    public void setLivro(Livros livro) {
        this.livro = livro;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    
}
