package com.biblioteca.biblioteca.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;


@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivrosService livrosService;

    public Page<Emprestimo> getAll(Pageable pageable){
        return this.emprestimoRepository.findAll(pageable);
    }

    public Optional<Emprestimo> findbyId(Long id){
        return this.emprestimoRepository.findById(id);
    }

    @Transactional
    public void saveLoad(Emprestimo emprestimo){
        Optional<Livros> livroOptional = this.livrosService.findbyId(emprestimo.getLivro().getIsbn());
        if (livroOptional.isPresent()){
            Livros livro = livroOptional.get();

            if ( livro.getQuantidadeDisponivel() > 0){
                livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);
                this.emprestimoRepository.save(emprestimo);
            }
            else{

            }
        }
        else{

        }
    }

    public void deleteLoad(Emprestimo emprestimo){
        this.emprestimoRepository.delete(emprestimo);
    }
}
