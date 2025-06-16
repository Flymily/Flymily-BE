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
@Table(name = "agencia")
@Getter @Setter
public class Agencia {

    @Id
    @SequenceGenerator (name="agencia_id_sequence", sequenceName = "agencia_id_sequence", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agencia_id_sequence")
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "agencia")
    private List<Viaje> viajes;

}