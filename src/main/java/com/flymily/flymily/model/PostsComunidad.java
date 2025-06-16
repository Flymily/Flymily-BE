package com.flymily.flymily.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "postsComunidad")
@Getter @Setter
public class PostsComunidad {
    
    @Id
    @SequenceGenerator (name="comunidad_id_sequence", sequenceName = "comunidad_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comunidad_id_sequence")
    private Long id;

    @Column (name="Título")
    @NotBlank
    @Size (max = 100, message = "(!) ERROR: el campo del título no puede tener más de 100 caracteres")
    private String tituloPost;

    @Column (name="Contenido")
    @NotBlank
    @Size (max = 5000, message = "(!) ERROR: el campo del contenido no puede tener más de 5000 caracteres")
    private String contenidoPost;

    @Column (name="Imagen")
    private String imgPathComunidad;

}
