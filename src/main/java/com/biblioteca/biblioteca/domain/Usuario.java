package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import java.util.Set;

import com.biblioteca.biblioteca.dtos.UsuarioDTO;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue()
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long  userId;

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
        this.nome = user.nome();
        this.email = user.email();
        this.telefone = user.telefone();
    }

    public Usuario(){
        
    }
}


