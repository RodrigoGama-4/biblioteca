package com.biblioteca.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.LivroAutor;
import com.biblioteca.biblioteca.repository.LivroAutorRepository;

@Service
public class LivroAutorService {
    @Autowired
    private LivroAutorRepository livroAutorRepository;

    public Page<LivroAutor> getAll(Pageable pageable){
        return this.livroAutorRepository.findAll(pageable);
    }

    public Optional<LivroAutor> findbyId(Long id){
        return this.livroAutorRepository.findById(id);
    }

    public void saveBookAuth(LivroAutor livroAutor){
        this.livroAutorRepository.save(livroAutor);
    }

    public void deleteBookAuth(LivroAutor livroAutor){
        this.livroAutorRepository.delete(livroAutor);
    }
}
