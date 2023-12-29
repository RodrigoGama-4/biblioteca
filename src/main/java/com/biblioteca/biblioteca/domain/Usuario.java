package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;
import java.util.UUID;

import com.biblioteca.biblioteca.dtos.UsuarioDTO;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    // Outros atributos do usuário

    @OneToMany(mappedBy = "usuario")
    private Set<Emprestimo> emprestimos;

    public Usuario(UsuarioDTO user){

    }
}


