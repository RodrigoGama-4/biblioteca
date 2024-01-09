package com.biblioteca.biblioteca.services;

import java.util.Optional;

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

    public Optional<Autor> findbyId(Long id){
        return this.autorRepository.findById(id);
    }

    public Autor saveAuth(Autor autor){
        return this.autorRepository.save(autor);
    }

    public void deleteAuth(Autor autor){
        this.autorRepository.delete(autor);
    }
}
