package com.biblioteca.biblioteca.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.repository.EmprestimoRepository;


@Service
public class EmprestimoService {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    public Page<Emprestimo> getAll(Pageable pageable){
        return this.emprestimoRepository.findAll(pageable);
    }

    public Optional<Emprestimo> findbyId(UUID id){
        return this.emprestimoRepository.findById(id);
    }

    public void saveLoad(Emprestimo emprestimo){
        this.emprestimoRepository.save(emprestimo);
    }

    public void deleteLoad(Emprestimo emprestimo){
        this.emprestimoRepository.delete(emprestimo);
    }
}
