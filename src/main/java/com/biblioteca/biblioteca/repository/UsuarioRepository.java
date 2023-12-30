package com.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
