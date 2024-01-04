package com.biblioteca.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biblioteca.biblioteca.domain.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
    boolean existsByLivroIsbn(String isbn);

}
