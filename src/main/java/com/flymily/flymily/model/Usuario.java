package com.flymily.flymily.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 20)
    private String rol;

    
}