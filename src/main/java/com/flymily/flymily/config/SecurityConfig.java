package com.flymily.flymily.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.flymily.flymily.model.Usuario;
import com.flymily.flymily.service.UsuarioService;

@Configuration
public class SecurityConfig {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder
            .userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder);

        return authenticationManagerBuilder.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario usuario = usuarioService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
            return new org.springframework.security.core.userdetails.User(
                usuario.getUsername(),
                usuario.getPassword(),
                new ArrayList<>());
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login", "/api/viajes/filtrar", "/api/viajes/filtrar/detalle/**",
            "/api/posts-comunidad", "/api/posts-comunidad/view/**", "/api/viajes/filtrar/detalle/all",
            "/api/tipos-viaje/all").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/viajes/filtrar").permitAll()
            .requestMatchers("/api/posts-comunidad/auth/update/**", "/api/posts-comunidad/auth/delete/**", "/api/posts-comunidad/auth/create").authenticated()
            .anyRequest().authenticated()
        )
        .httpBasic(basic -> basic.init(http));

    return http.build();
    }

}

