package com.biblioteca.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.repository.LivrosRepository;

@Service
public class LivrosService {
    @Autowired
    private LivrosRepository livrosRepository;

    public Page<Livros> getAll(Pageable pageable){
        return this.livrosRepository.findAll(pageable);
    }

    public Optional<Livros> findbyId(String isbn){
        return this.livrosRepository.findById(isbn);
    }

    public void saveBook(Livros livro){
        this.livrosRepository.save(livro);
    }
}
