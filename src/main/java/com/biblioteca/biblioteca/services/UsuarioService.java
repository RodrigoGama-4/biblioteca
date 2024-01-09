package com.biblioteca.biblioteca.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;
import com.biblioteca.biblioteca.services.mail.MailService;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MailService mailService;

    public Page<Usuario> getAll(Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> findbyId(Long id){
        return this.usuarioRepository.findById(id);
    }

    public UserDetails findbyEmail(String email){
        return this.usuarioRepository.findByEmail(email);
    }

    public Usuario saveUser(Usuario user){
        Usuario usuarioSalvo = this.usuarioRepository.save(user);
        // Enviar o e-mail de forma assíncrona
        CompletableFuture.runAsync(() -> this.mailService.senderMail(user.getNome(), user.getEmail(), "CADASTRO NA NOSSA BIBLIOTECA", "Parabéns, agora você pode alugar livros a vontade"));
        return usuarioSalvo;
    }

    public void deleteUser(Usuario user){
        this.usuarioRepository.delete(user);
    }

}
