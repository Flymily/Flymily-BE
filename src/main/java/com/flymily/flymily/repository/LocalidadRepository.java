package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.Localidad;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long> {

    Optional<Localidad> findByPaisIgnoreCase(String pais);
    Optional<Localidad> findByCiudadIgnoreCase(String ciudad);
    Optional<Localidad> findByCiudadAndPais(String ciudad, String pais);

}