package com.biblioteca.biblioteca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.biblioteca.biblioteca.domain.Usuario;
import com.biblioteca.biblioteca.dtos.AuthenticationDTO;
import com.biblioteca.biblioteca.dtos.LoginResponseDTO;
import com.biblioteca.biblioteca.dtos.UsuarioDTO;
import com.biblioteca.biblioteca.infra.security.TokenService;
import com.biblioteca.biblioteca.services.CpfService;
import com.biblioteca.biblioteca.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private CpfService cpfService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody @Valid UsuarioDTO data){
        if(this.usuarioService.findbyEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        
        //Verificar se o cpf é valido ou nao
        if (!this.cpfService.searchCpf(data.cpf()).valid()){
            return ResponseEntity.badRequest().build();
        }

        String senhaEncript = new BCryptPasswordEncoder().encode(data.senha());

        Usuario user = new Usuario(data.cpf(),data.nome(), senhaEncript, data.email(), data.telefone());
        this.usuarioService.saveUser(user);
    
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid AuthenticationDTO data ){
        var userNameSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(userNameSenha);

        var token = tokenService.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
 
}
