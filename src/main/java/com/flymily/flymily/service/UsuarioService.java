package com.flymily.flymily.service;

import com.flymily.flymily.dto.LoginDTO;
import com.flymily.flymily.exceptions.InvalidCredentialsException;
import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario) {
        String hashedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(hashedPassword);
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

    public boolean usernameExists(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Usuario authenticate(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException("username o contraseña incorrecto(s)"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("username o contraseña incorrecto(s)");
        }

        return usuario;
    }

}