package com.biblioteca.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.dtos.UsuarioDTO;
import com.biblioteca.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("biblioteca")
public class BibliUsuariosController {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping("/usuarios")
    public ResponseEntity<Page<Usuario>> getAllUsers(Pageable pageable){
        Page<Usuario> usuarios = this.usuarioService.getAll(pageable);
        return new ResponseEntity<Page<Usuario>>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id){
        Optional<Usuario> user = this.usuarioService.findbyId(id);
        return user.map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/usuarios")
    public void saveUser(@RequestBody UsuarioDTO usuarioDTO ){
        Usuario user = new Usuario(usuarioDTO);
        this.usuarioService.saveUser(user);
    }

    @DeleteMapping("/usuarios/{id}")
    public void deleteUser(@PathVariable Long id){
        Optional<Usuario> user = this.usuarioService.findbyId(id);
        user.ifPresent(usuario -> this.usuarioService.deleteUser(usuario));
    }

}
