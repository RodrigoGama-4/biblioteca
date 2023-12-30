package com.biblioteca.biblioteca.dtos;

import com.biblioteca.biblioteca.domain.Autor;
import com.biblioteca.biblioteca.domain.Livros;

public record LivrosAutorDTO(Long id, Livros isbn, Autor autorId) {
    
}
