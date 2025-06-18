package com.flymily.flymily.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/login", "/api/viajes/filtrar", "/api/viajes/filtrar/detalle/**",
            "/api/posts-comunidad", "/api/posts-comunidad/view/**", "/api/viajes/filtrar/detalle/all").permitAll()
            .requestMatchers("/api/posts-comunidad/auth/create", "/api/posts-comunidad/auth/update/**", "/api/posts-comunidad/auth/delete/**").authenticated()
            .anyRequest().authenticated()
        )
        .httpBasic(basic -> basic.init(http));

    return http.build();
    }

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .csrf(csrf -> csrf.disable())
    //         .authorizeHttpRequests(auth -> auth
    //             .requestMatchers("/api/auth/login", "/api/viajes/filtrar", "/api/viajes/filtrar/detalle/**", "/api/viajes/filtrar/detalle/all",
    //             "/api/posts-comunidad", "/api/posts-comunidad/**").permitAll()
    //             .requestMatchers("/api/posts-comunidad/auth/create", "/api/posts-comunidad/auth/update/**", "/api/posts-comunidad/auth/delete/**").authenticated()
    //             .anyRequest().authenticated()
    //         )
    //         .formLogin(form -> form.disable())
    //         .httpBasic(basic -> basic.disable());
        
    //     return http.build();
    // }
}