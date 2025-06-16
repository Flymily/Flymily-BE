package com.flymily.flymily.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipoViaje")
@Getter @Setter

public class TipoViaje {
    
    @Id
    @SequenceGenerator (name="tipoViaje_id_sequence", sequenceName = "tipoViaje_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoViaje_id_sequence")
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String tipoViaje;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoViaje")
    private List<Viaje> viajes;

    public TipoViaje() {
    }

}