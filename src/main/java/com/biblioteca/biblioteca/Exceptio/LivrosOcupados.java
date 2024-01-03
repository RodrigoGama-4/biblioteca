package com.biblioteca.biblioteca.Exceptio;

public class LivrosOcupados extends RuntimeException {
    public LivrosOcupados(String message) {
        super(message);
    }
}
