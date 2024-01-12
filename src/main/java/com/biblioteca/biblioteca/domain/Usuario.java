package com.biblioteca.biblioteca.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.biblioteca.biblioteca.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue()
    @Column(name = "user_id", updatable = false, nullable = false)
    private Long  userId;

    @Column(name = "nome")
    private String nome;

    @Column(nullable = false) 
    private String senha;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    // Outros atributos do usuário
    @JsonIgnore
    @OneToMany(mappedBy = "usuario")
    private Set<Emprestimo> emprestimos;

    public Usuario(UsuarioDTO user){
        this.nome = user.nome();
        this.email = user.email();
        this.telefone = user.telefone();
        this.senha = user.senha();
    }

     public Usuario(Long userId){
        this.userId = userId;
        
    }

    public Usuario(String nome, String senha, String email, String telefone){
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
  
}


