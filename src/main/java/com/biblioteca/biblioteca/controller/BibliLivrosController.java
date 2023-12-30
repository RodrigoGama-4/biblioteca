package com.biblioteca.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.biblioteca.biblioteca.services.LivrosService;

@Service
public class BibliLivrosController {

    @Autowired
    private LivrosService livrosService;

    /*CRUD de livros */
    @GetMapping("/livros")
    public ResponseEntity<Page<Livros>> getAllBooks(Pageable pageable){
        Page<Livros> livros = this.livrosService.getAll(pageable);
        return new ResponseEntity<Page<Livros>>(livros, HttpStatus.OK);
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Livros> getBookById(@PathVariable String isbn){
        Optional<Livros> livro = this.livrosService.findbyId(isbn);
        return livro.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("livros")
    public void saveBook(@RequestBody LivrosDTO livrosDTO){
        Livros livro = new Livros(livrosDTO);
        this.livrosService.saveBook(livro);
    }

    @DeleteMapping("/livros/{id}")
    public void deleteBook(@PathVariable String isbn){
        Optional<Livros> livro = this.livrosService.findbyId(isbn);
        livro.ifPresent(livroDelete -> this.livrosService.deleteBook(livroDelete));
    }
}
