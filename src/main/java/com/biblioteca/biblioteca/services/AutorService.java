package com.biblioteca.biblioteca.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.Autor;
import com.biblioteca.biblioteca.repository.AutorRepository;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Page<Autor> getAll(Pageable pageable){
        return this.autorRepository.findAll(pageable);
    }

    public Optional<Autor> findbyId(UUID id){
        return autorRepository.findById(id);
    }
}