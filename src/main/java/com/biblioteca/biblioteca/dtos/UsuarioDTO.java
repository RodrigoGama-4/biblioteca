package com.biblioteca.biblioteca.dtos;

import java.util.UUID;

public record UsuarioDTO(UUID id, String nome, String email, String telefone) {
    
}
