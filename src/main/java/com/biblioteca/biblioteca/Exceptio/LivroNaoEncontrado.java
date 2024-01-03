package com.biblioteca.biblioteca.Exceptio;

public class LivroNaoEncontrado extends RuntimeException{
    public LivroNaoEncontrado(String isbn) {
        super("Livro não encontrado para o ISBN: " + isbn);
    }
}
