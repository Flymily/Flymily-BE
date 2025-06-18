// package com.flymily.flymily.service;

// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.flymily.flymily.model.Usuario;
// import com.flymily.flymily.repository.UsuarioRepository;

// import lombok.RequiredArgsConstructor;

// @Service
// @RequiredArgsConstructor
// public class CustomUserDetailsService implements UserDetailsService {
    
//     private final UsuarioRepository usuarioRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Usuario usuario = usuarioRepository.findByUsername(username)
//             .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
        
//         return User.builder()
//             .username(usuario.getUsername())
//             .password(usuario.getPassword())
//             .build();
//     }
// }