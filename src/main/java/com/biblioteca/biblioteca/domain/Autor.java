package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import com.biblioteca.biblioteca.dtos.AutorDTO;

@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @Column(name = "autor_id")
    private UUID autorId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;


    @OneToMany(mappedBy = "autor")
    private Set<LivroAutor> livros;

    public Autor(AutorDTO autorDTO){
        
    }
}
