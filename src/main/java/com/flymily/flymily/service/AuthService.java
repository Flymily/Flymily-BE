// package com.flymily.flymily.service;

// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.flymily.flymily.dto.LoginDTO;
// import com.flymily.flymily.exceptions.InvalidCredentialsException;
// import com.flymily.flymily.model.Usuario;
// import com.flymily.flymily.repository.UsuarioRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class AuthService {
    
//     private final UsuarioRepository usuarioRepository;
//     private final PasswordEncoder passwordEncoder;

//     public Usuario authenticate(LoginDTO loginDTO) {
//         Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
//             .orElseThrow(() -> new InvalidCredentialsException("(!) ERROR: username o contraseña incorrecto(s)"));

//         if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
//             throw new InvalidCredentialsException("(!) ERROR: username o contraseña incorrecto(s)");
//         }

//         return usuario;
//     }
// }
