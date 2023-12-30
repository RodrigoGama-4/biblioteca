package com.biblioteca.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.biblioteca.domain.LivroAutor;
import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.dtos.LivrosAutorDTO;
import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.biblioteca.biblioteca.services.LivroAutorService;
import com.biblioteca.biblioteca.services.LivrosService;

@RestController
@RequestMapping("biblioteca")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BibliLivrosAutorController {
    @Autowired
    private LivroAutorService livroAutorService;

    /*CRUD de livros */
    @GetMapping("/livros-autores")
    public ResponseEntity<Page<LivroAutor>> getAllBooks(Pageable pageable){
        Page<LivroAutor> livros = this.livroAutorService.getAll(pageable);
        return new ResponseEntity<Page<LivroAutor>>(livros, HttpStatus.OK);
    }

    @GetMapping("/livros-autores/{id}")
    public ResponseEntity<LivroAutor> getBookById(@PathVariable Long id){
        Optional<LivroAutor> livro = this.livroAutorService.findbyId(id);
        return livro.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("livros-autores")
    public void saveBook(@RequestBody LivrosAutorDTO livroAutorDTO){
        LivroAutor livro = new LivroAutor(livroAutorDTO);
        this.livroAutorService.saveBookAuth(livro);
    }

    @DeleteMapping("/livros-autores/{id}")
    public void deleteBook(@PathVariable Long isbn){
        Optional<LivroAutor> livro = this.livroAutorService.findbyId(isbn);
        livro.ifPresent(livroDelete -> this.livroAutorService.deleteBookAuth(livroDelete));
    }
}
