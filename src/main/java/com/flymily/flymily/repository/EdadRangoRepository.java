package com.flymily.flymily.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.flymily.flymily.model.EdadRango;

public interface EdadRangoRepository extends JpaRepository<EdadRango, Long>{
    Optional<EdadRango> findByDescripcionIgnoreCase(String descripcion);
}
