package com.flymily.flymily.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flymily.flymily.dto.LoginDTO;
import com.flymily.flymily.exceptions.InvalidCredentialsException;
import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.service.UsuarioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")

public class LoginController {

    private final UsuarioService usuarioService;

    LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            Usuario usuario = usuarioService.authenticate(loginDTO);
            return ResponseEntity.ok("Login exitoso del usuario " + usuario.getUsername());
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + e.getMessage());
        }
    }
}