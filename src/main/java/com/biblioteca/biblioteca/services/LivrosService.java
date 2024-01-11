package com.biblioteca.biblioteca.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.LivroAutor;
import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.exceptions.LivrosAutoresException;
import com.biblioteca.biblioteca.repository.LivrosRepository;

@Service
public class LivrosService {
    @Autowired
    private LivrosRepository livrosRepository;

    @Autowired
    private LivroAutorService livroAutorService;

    public Page<Livros> getAll(Pageable pageable){
        return this.livrosRepository.findAll(pageable);
    }

    public Optional<Livros> findbyId(String isbn){
        return this.livrosRepository.findById(isbn);
    }

    public Livros saveBook(Livros livro){
        return this.livrosRepository.save(livro);
    }

    public void deleteBook(Livros livro){   
        List<LivroAutor> livrosAutores = this.livroAutorService.livrosAutores(livro);
        if (livrosAutores.isEmpty()){
            this.livrosRepository.delete(livro);
        }
        else{
            throw new LivrosAutoresException();
        }

    }
}
