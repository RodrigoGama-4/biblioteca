package com.biblioteca.biblioteca.controller;

import java.util.Optional;
import java.util.UUID;

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

import com.biblioteca.biblioteca.domain.Autor;
import com.biblioteca.biblioteca.dtos.AutorDTO;
import com.biblioteca.biblioteca.services.AutorService;

@Service
public class BibliAutoresController {
    
    @Autowired
    private AutorService autorService;
    
    /*CRUD de autores */
    @GetMapping("/autores")
    public ResponseEntity<Page<Autor>> getAllAuth(Pageable pageable){
        Page<Autor> autores = this.autorService.getAll(pageable);
        return new ResponseEntity<Page<Autor>>(autores, HttpStatus.OK);
    }

    @GetMapping("/autores/{id}")
    public ResponseEntity<Autor> getAuthById(@PathVariable UUID id){
        Optional<Autor> autor = this.autorService.findbyId(id);
        return autor.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("autores")
    public void saveAuth(@RequestBody AutorDTO autorDTO){
        Autor autor = new Autor(autorDTO);
        this.autorService.saveAuth(autor);
    }
    
    @DeleteMapping("/autores/{id}")
    public void deleteAuth(@PathVariable UUID id){
        Optional<Autor> autor = this.autorService.findbyId(id);
        autor.ifPresent(autorDelete -> this.autorService.deleteAuth(autorDelete));
    }
}
