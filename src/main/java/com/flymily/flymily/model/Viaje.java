package com.flymily.flymily.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table (name="Experiencias")
@Getter @Setter

public class Viaje {
    
    @Id
    @SequenceGenerator (name="experience_id_sequence", sequenceName = "experience_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_id_sequence")
    private Long experienceId;
    
    @Column (name="Título")
    @NotBlank
    @Size (max = 100, message = "(!) ERROR: el campo del título no puede tener más de 100 caracteres")
    private String title;

    @Column (name="N. de adultos")
    @NotNull (message = "(!) ERROR: el campo del máximo de participantes no puede estar vacío")
    @Min (value = 1, message = "(!) ERROR: el campo del máximo de participantes debe tener un valor mínimo de 1")
    private Integer numAdultos;

    @Column (name="N. de ninos")
    @NotNull (message = "(!) ERROR: el campo del máximo de participantes no puede estar vacío")
    @Min (value = 1, message = "(!) ERROR: el campo los adultos debe tener un valor mínimo de 1")
    private Integer numNinos;

    @Column (name="Edad")
    @NotNull (message = "(!) ERROR: el campo de las edades no puede estar vacío")
    private Integer edad;

    @Column (name="Presupuesto")
    @NotNull (message = "(!) ERROR: el campo del presupuesto no puede estar vacío")
    private Integer presupuesto;

    @Column (name = "Fecha de Ida")
    @NotNull(message = "(!) ERROR: el campo de la fecha de ida no puede estar vacío")
    private LocalDate fechaDeIda;

    @Column (name = "Fecha de Vuelta")
    @NotNull(message = "(!) ERROR: el campo de la fecha de vuelta no puede estar vacío")
    private LocalDate fechaDeVuelta;

    @Column (name = "Movilidad Reducida?")
    @NotNull(message = "(!) ERROR: el campo de la movilidad reducida no puede estar vacío")
    private boolean movilidadReducida;

    @Column (name = "Riesgo Social?")
    @NotNull(message = "(!) ERROR: el campo de la movilidad reducida no puede estar vacío")
    private boolean riesgoSocial;

    @Column (name="Imagen")
    private String imgPath;

    @ManyToOne
    @JoinColumn(name = "localidad_salida_id", nullable = false)
    private Localidad localidadSalida;

    @ManyToOne
    @JoinColumn(name = "localidad_destino_id", nullable = false)
    private Localidad localidadDestino;

    @ManyToOne
    @JoinColumn(name = "tipo_experiencia_id", nullable = false)
    private TipoExperiencia tipoExperiencia;

    @ManyToOne
    @JoinColumn(name = "transporte_id", nullable = false)
    private Transporte transporte;

    //AÑADIR RELACIONES CON TIPO, TRANSPORTE Y LOCALIDAD - CREAR SALIDA Y LLEGADA

    public Viaje(){};

}
