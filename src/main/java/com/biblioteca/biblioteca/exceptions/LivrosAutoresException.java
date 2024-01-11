package com.biblioteca.biblioteca.exceptions;

import com.biblioteca.biblioteca.domain.Livros;

public class LivrosAutoresException extends RuntimeException {
    public LivrosAutoresException(){
        super("O livro está ligado com um autor. Primeiro exclua a ligação entre os dois para poder excluir o livro. Tabela: LivroAutor");
    }
}
