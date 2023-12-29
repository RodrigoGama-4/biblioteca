package com.biblioteca.biblioteca.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.biblioteca.domain.Autor;
import com.biblioteca.biblioteca.domain.Emprestimo;
import com.biblioteca.biblioteca.domain.Livros;
import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.dtos.AutorDTO;
import com.biblioteca.biblioteca.dtos.EmprestimoDTO;
import com.biblioteca.biblioteca.dtos.LivrosDTO;
import com.biblioteca.biblioteca.dtos.UsuarioDTO;
import com.biblioteca.biblioteca.services.AutorService;
import com.biblioteca.biblioteca.services.EmprestimoService;
import com.biblioteca.biblioteca.services.LivrosService;
import com.biblioteca.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("biblioteca")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BibliotecaController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private LivrosService livrosService;

    @Autowired
    private EmprestimoService emprestimoService;
    
    /*CRUD de usuários */
    @GetMapping("/usuarios")
    public ResponseEntity<Page<Usuario>> getAllUsers(Pageable pageable){
        Page<Usuario> usuarios = this.usuarioService.getAll(pageable);
        return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable UUID id){
        Optional<Usuario> user = this.usuarioService.findbyId(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/usuarios")
    public void saveUser(@RequestBody UsuarioDTO usuarioDTO ){
        Usuario user = new Usuario(usuarioDTO);
        this.usuarioService.saveUser(user);
    }
    

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

    /*CRUD de livros */
    @GetMapping("/livros")
    public ResponseEntity<Page<Livros>> getAllBooks(Pageable pageable){
        Page<Livros> livros = this.livrosService.getAll(pageable);
        return new ResponseEntity<Page<Livros>>(livros, HttpStatus.OK);
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Livros> getBookById(@PathVariable String isbn){
        Optional<Livros> livro = this.livrosService.findbyId(isbn);
        return livro.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("livros")
    public void saveBook(@RequestBody LivrosDTO livrosDTO){
        Livros livro = new Livros(livrosDTO);
        this.livrosService.saveBook(livro);
    }

    /*CRUD de usuários */
    @GetMapping("/emprestimos")
    public ResponseEntity<Page<Emprestimo>> getAllLoan(Pageable pageable){
        Page<Emprestimo> emprestimos = this.emprestimoService.getAll(pageable);
        return new ResponseEntity<Page<Emprestimo>>(emprestimos, HttpStatus.OK);
    }

    @GetMapping("/emprestimos/{id}")
    public ResponseEntity<Emprestimo> getLoanById(@PathVariable UUID id){
        Optional<Emprestimo> emprestimo = this.emprestimoService.findbyId(id);
        return emprestimo.map(e -> new ResponseEntity<>(e, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("emprestimos")
    public void saveLoad(@RequestBody EmprestimoDTO emprestimoDTO){
        Emprestimo emprestimo = new Emprestimo(emprestimoDTO);
        this.emprestimoService.saveLoad(emprestimo);
    }
}
