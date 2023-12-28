package com.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.domain.Livros;

public interface LivrosRepository extends JpaRepository<Livros, String> {
    
}
