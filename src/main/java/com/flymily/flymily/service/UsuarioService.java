package com.flymily.flymily.service;

import com.flymily.flymily.dto.LoginDTO;
import com.flymily.flymily.dto.RegisterDTO;
import com.flymily.flymily.exceptions.InvalidCredentialsException;
import com.flymily.flymily.exceptions.UserAlreadyExistsException;
import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor 

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

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
    
    public Usuario register(RegisterDTO registerDTO) {
        if (usernameExists(registerDTO.getUsername())) {
            throw new UserAlreadyExistsException("(!) ERROR: El nombre de usuario ya está en uso");
        }
        
        if (usuarioRepository.existsByEmail(registerDTO.getEmail())) {
            throw new UserAlreadyExistsException("(!) ERROR: El email ya está registrado");
        }

        Usuario newUser = new Usuario();
        newUser.setUsername(registerDTO.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        newUser.setEmail(registerDTO.getEmail());

        return usuarioRepository.save(newUser);
    }

    public Usuario authenticate(LoginDTO loginDTO) {
        Usuario usuario = usuarioRepository.findByUsername(loginDTO.getUsername())
            .orElseThrow(() -> new InvalidCredentialsException("(!) ERROR: username o contraseña incorrecto(s)"));

        if (!passwordEncoder.matches(loginDTO.getPassword(), usuario.getPassword())) {
            throw new InvalidCredentialsException("(!) ERROR: username o contraseña incorrecto(s)");
        }

        return usuario;
    }


}