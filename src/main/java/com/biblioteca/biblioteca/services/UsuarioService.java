package com.biblioteca.biblioteca.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> getAll(Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }

    public Optional<Usuario> findbyId(UUID id){
        return usuarioRepository.findById(id);
    }


}