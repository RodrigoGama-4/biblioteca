package com.biblioteca.biblioteca.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

    @DeleteMapping("/usuarios/{id}")
    public void deleteUser(@PathVariable Long id){
        Optional<Usuario> user = this.usuarioService.findbyId(id);
        user.ifPresent(usuario -> this.usuarioService.deleteUser(usuario));
    }

    @Transactional
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> putUser(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO ) {
        Optional<Usuario> usuarioOptional = this.usuarioService.findbyId(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            //CRIPTOGRAFANDO SENHA
            String senhaEncript = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

            usuario.setEmail(usuarioDTO.email());
            usuario.setNome(usuarioDTO.nome());
            usuario.setTelefone(usuarioDTO.telefone());
            usuario.setSenha(senhaEncript);

            Usuario usuarioAtualizado = this.usuarioService.saveUser(usuario);
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
