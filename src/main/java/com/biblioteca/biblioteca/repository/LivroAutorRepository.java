package com.biblioteca.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.domain.LivroAutor;
import com.biblioteca.biblioteca.domain.Livros;

public interface LivroAutorRepository extends JpaRepository<LivroAutor, Long>{
    List<LivroAutor> findByLivro(Livros livro);
}
