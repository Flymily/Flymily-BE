package com.flymily.flymily.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.flymily.flymily.model.PostsComunidad;

public interface PostsComunidadRepository extends JpaRepository<PostsComunidad, Long>{

    Optional<PostsComunidad> findByTituloPost(String tituloPost);
    boolean existsByTituloPost(String tituloPost);
    boolean existsByContenidoPost(String contenidoPost);

}
