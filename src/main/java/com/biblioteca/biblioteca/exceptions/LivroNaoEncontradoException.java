package com.biblioteca.biblioteca.exceptions;

public class LivroNaoEncontradoException extends RuntimeException{
    public LivroNaoEncontradoException(String isbn) {
        super("Livro não encontrado para o ISBN: " + isbn);
    }
}
