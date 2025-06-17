package com.flymily.flymily.controller;

import com.flymily.flymily.config.JwtService;
import com.flymily.flymily.dto.*;
import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginDTO loginDTO) {
        Usuario usuario = usuarioService.authenticate(loginDTO);
        String jwtToken = jwtService.generateToken(usuario); // Ahora Usuario es UserDetails
        
        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .username(usuario.getUsername())
                .message("Login exitoso")
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {
        Usuario usuario = usuarioService.register(registerDTO);
        String jwtToken = jwtService.generateToken(usuario);
        
        return ResponseEntity.ok(AuthResponse.builder()
                .token(jwtToken)
                .username(usuario.getUsername())
                .message("Registro exitoso")
                .build());
    }
}