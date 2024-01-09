package com.biblioteca.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.dtos.EmprestimoDTO;
import com.biblioteca.biblioteca.exceptions.LivrosOcupadosException;
import com.biblioteca.biblioteca.services.EmprestimoService;

@RestController
@RequestMapping("biblioteca")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BibliEmpreController {
    @Autowired
    private EmprestimoService emprestimoService;

    /*CRUD de usuários */
    @GetMapping("/emprestimos")
    public ResponseEntity<Page<Emprestimo>> getAllLoan(Pageable pageable){
        Page<Emprestimo> emprestimos = this.emprestimoService.getAll(pageable);
        return new ResponseEntity<Page<Emprestimo>>(emprestimos, HttpStatus.OK);
    }

    @GetMapping("/emprestimos/{id}")
    public ResponseEntity<Emprestimo> getLoanById(@PathVariable Long id){
        Optional<Emprestimo> emprestimo = this.emprestimoService.findbyId(id);
        return emprestimo.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("emprestimos")
    public void saveLoad(@RequestBody EmprestimoDTO emprestimoDTO) throws LivrosOcupadosException{
        Emprestimo emprestimo = new Emprestimo(emprestimoDTO);
        this.emprestimoService.saveLoad(emprestimo);
    }

    @DeleteMapping("/emprestimos/{id}")
    public void deleteLoad(@PathVariable Long id){
        Optional<Emprestimo> emprestimo = this.emprestimoService.findbyId(id);
        emprestimo.ifPresent(emprestimoDelete -> this.emprestimoService.deleteLoad(emprestimoDelete));
    }

    @Transactional
    @PutMapping("/emprestimo/{id}")
    public ResponseEntity<Emprestimo> putAuth(@PathVariable Long id, @RequestBody EmprestimoDTO emprestimoDTO ) {
        Optional<Emprestimo> emprestimoOptional = this.emprestimoService.findbyId(id);

        if (emprestimoOptional.isPresent()) {
            Emprestimo emprestimo = emprestimoOptional.get();
            emprestimo.setDataDevolucao(emprestimoDTO.data_devo());
            emprestimo.setDataEmprestimo(emprestimoDTO.data_emprestimo());
            emprestimo.setLivro(emprestimoDTO.isbn());
            emprestimo.setUsuario(emprestimoDTO.idUsuario());


            Emprestimo emprestimoAtualizado = this.emprestimoService.saveLoad(emprestimo);

            return new ResponseEntity<>(emprestimoAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}