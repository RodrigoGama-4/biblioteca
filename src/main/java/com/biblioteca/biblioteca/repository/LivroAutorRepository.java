package com.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.domain.LivroAutor;

public interface LivroAutorRepository extends JpaRepository<LivroAutor, Long>{
    
}
