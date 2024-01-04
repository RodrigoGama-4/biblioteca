package com.biblioteca.biblioteca.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException(String msg){
        super(msg);
    }
}
