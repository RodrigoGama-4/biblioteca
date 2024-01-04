package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import com.biblioteca.biblioteca.dtos.AutorDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue()
    @Column(name = "autor_id")
    private Long autorId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "nascimento")
    private String nascimento;

    @Column(name = "nacionalidade")
    private String nacionalidade;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private Set<LivroAutor> livros;

    public Autor(AutorDTO autorDTO){
        this.nome = autorDTO.nome();
        this.nacionalidade = autorDTO.nacionalidade();
        this.nascimento = autorDTO.dt_nasci();
    }

    public Autor(Long autorId) {
        this.autorId = autorId;
    }
    
}
