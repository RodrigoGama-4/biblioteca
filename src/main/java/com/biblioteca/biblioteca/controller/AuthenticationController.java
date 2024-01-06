package com.biblioteca.biblioteca.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.dtos.UsuarioDTO;
import com.biblioteca.biblioteca.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid UsuarioDTO data){
        if(this.usuarioService.findbyEmail(data.email()) != null) return ResponseEntity.badRequest().build();
        
        String senhaEncript = new BCryptPasswordEncoder().encode(data.senha());
    
        Usuario user = new Usuario(data.nome(), senhaEncript, data.email(), data.telefone());
        this.usuarioService.saveUser(user);
    
        return ResponseEntity.ok().build();
    }
 
}
