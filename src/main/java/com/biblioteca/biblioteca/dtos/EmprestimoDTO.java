package com.biblioteca.biblioteca.dtos;

import java.sql.Date;

import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.domain.Usuario;

public record EmprestimoDTO(Livros isbn, Usuario idUsuario, Date data_emprestimo, Date data_devo ) {
    
}
