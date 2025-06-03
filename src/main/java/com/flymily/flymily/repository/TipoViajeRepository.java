package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.flymily.flymily.model.TipoViaje;

@Repository
public interface TipoViajeRepository extends JpaRepository<TipoViaje, Long> {
    Optional<TipoViaje> findByTipoViajeIgnoreCase(String tipoViaje);

}