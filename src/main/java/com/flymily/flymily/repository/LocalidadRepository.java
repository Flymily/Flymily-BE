package com.flymily.flymily.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flymily.flymily.model.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    List<Localidad> findByPaisIgnoreCase(String pais);
    List<Localidad> findByCiudadIgnoreCase(String ciudad);

}