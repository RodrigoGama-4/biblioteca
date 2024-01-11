package com.biblioteca.biblioteca.exceptions;

import com.biblioteca.biblioteca.domain.Usuario;

public class UsuarioEmprestimoException extends RuntimeException {
    public UsuarioEmprestimoException(Usuario user){
        super("O usuário: " + user.getNome() + "não devolveu seus empréstimos ainda. Por favor devolva os empréstimos para excluir o usuario.");
    }
}
