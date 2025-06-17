package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.flymily.flymily.model.EdadRango;

public interface EdadRangoRepository extends JpaRepository<EdadRango, Long>, EdadRangoRepositoryCustom{
    Optional<EdadRango> findByDescripcionIgnoreCase(String descripcion);
    
    @Query("SELECT r FROM EdadRango r WHERE :edad BETWEEN r.edadMin AND r.edadMax")
    Optional<EdadRango> findByEdad(@Param("edad") Integer edad);
    
}
