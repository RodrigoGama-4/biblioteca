package com.biblioteca.biblioteca.dtos;

import java.sql.Date;
import java.util.UUID;

import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.domain.Usuario;

public record EmprestimoDTO(UUID emprestimo_id, Livros isbn, Usuario idUsuario, Date data_emprestimo, Date data_devo ) {
    
}
