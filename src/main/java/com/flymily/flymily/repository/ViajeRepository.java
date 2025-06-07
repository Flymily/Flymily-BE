package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.Localidad;
import com.flymily.flymily.model.Viaje;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer>{

    Optional<Localidad> findByTitleIgnoreCase(String title);
    Optional<Localidad> findByDescriptionIgnoreCase(String title);
    
}
