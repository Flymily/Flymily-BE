package com.flymily.Flymily_BE;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.flymily.flymily.dto.LoginDTO;
import com.flymily.flymily.dto.RegisterDTO;
import com.flymily.flymily.exceptions.InvalidCredentialsException;
import com.flymily.flymily.exceptions.UserAlreadyExistsException;
import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.repository.UsuarioRepository;
import com.flymily.flymily.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;
    private RegisterDTO registerDTO;
    private LoginDTO loginDTO;

    @BeforeEach
    public void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("testuser");
        usuario.setPassword("encodedpassword");
        usuario.setEmail("test@example.com");

        registerDTO = new RegisterDTO();
        registerDTO.setUsername("testuser");
        registerDTO.setPassword("password123");
        registerDTO.setEmail("test@example.com");

        loginDTO = new LoginDTO();
        loginDTO.setUsername("testuser");
        loginDTO.setPassword("password123");
    }

    @Test
    void whenRegisterNewUser_thenReturnSavedUser() {
        when(usuarioRepository.existsByUsername("testuser")).thenReturn(false);
        when(usuarioRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("encodedpassword");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario savedUser = usuarioService.register(registerDTO);

        assertNotNull(savedUser);
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("encodedpassword", savedUser.getPassword());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void whenRegisterWithExistingUsername_thenThrowException() {
        when(usuarioRepository.existsByUsername("existinguser")).thenReturn(true);
        registerDTO.setUsername("existinguser");

        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> {
            usuarioService.register(registerDTO);
        });
        
        assertEquals("(!) ERROR: El nombre de usuario ya está en uso", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void whenAuthenticateWithValidCredentials_thenReturnUser() {
        when(usuarioRepository.findByUsername("testuser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("password123", "encodedpassword")).thenReturn(true);

        Usuario authenticatedUser = usuarioService.authenticate(loginDTO);

        assertNotNull(authenticatedUser);
        assertEquals("testuser", authenticatedUser.getUsername());
    }

    @Test
    void whenAuthenticateWithInvalidUsername_thenThrowException() {
        when(usuarioRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());
        loginDTO.setUsername("nonexistent");

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
            usuarioService.authenticate(loginDTO);
        });

        assertEquals("(!) ERROR: username o contraseña incorrecto(s)", exception.getMessage());
    }

    @Test
    void whenAuthenticateWithInvalidPassword_thenThrowException() {
        when(usuarioRepository.findByUsername("testuser")).thenReturn(Optional.of(usuario));
        when(passwordEncoder.matches("wrongpassword", "encodedpassword")).thenReturn(false);
        loginDTO.setPassword("wrongpassword");

        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
            usuarioService.authenticate(loginDTO);
        });

        assertEquals("(!) ERROR: username o contraseña incorrecto(s)", exception.getMessage());
    }
}