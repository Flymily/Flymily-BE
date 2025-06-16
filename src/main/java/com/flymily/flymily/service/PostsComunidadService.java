package com.flymily.flymily.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.flymily.flymily.dto.PostsComunidadDTO;
import com.flymily.flymily.exceptions.DuplicateResourceException;
import com.flymily.flymily.exceptions.ResourceNotFoundException;
import com.flymily.flymily.model.PostsComunidad;
import com.flymily.flymily.repository.PostsComunidadRepository;

@Service
public class PostsComunidadService {
    
    private final PostsComunidadRepository postsComunidadRepository;

    public PostsComunidadService(PostsComunidadRepository postsComunidadRepository){
        this.postsComunidadRepository = postsComunidadRepository;
    }

        public ResponseEntity<?> createPost(PostsComunidadDTO postsComunidadDTO) {
        try {
            if (postsComunidadRepository.existsByTituloPost(postsComunidadDTO.getTituloPost())) {
                throw new DuplicateResourceException("Ya existe un post con el título: " + postsComunidadDTO.getTituloPost());
            }

            if (postsComunidadRepository.existsByContenidoPost(postsComunidadDTO.getContenidoPost())) {
                throw new DuplicateResourceException("Ya existe un post con el mismo contenido");
            }

            PostsComunidad post = new PostsComunidad();
            post.setTituloPost(postsComunidadDTO.getTituloPost());
            post.setContenidoPost(postsComunidadDTO.getContenidoPost());
            post.setImgPathComunidad(postsComunidadDTO.getImgPathComunidad());

            PostsComunidad savedPost = postsComunidadRepository.save(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el post: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllPosts() {
        try {
            List<PostsComunidad> posts = postsComunidadRepository.findAll();
            return ResponseEntity.ok(posts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los posts: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getPostById(Long id) {
        try {
            PostsComunidad post = postsComunidadRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado con id: " + id));
            return ResponseEntity.ok(post);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener el post: " + e.getMessage());
        }
    }

    public ResponseEntity<?> updatePost(Long id, PostsComunidadDTO postsComunidadDTO) {
        try {
            PostsComunidad existingPost = postsComunidadRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Post no encontrado con id: " + id));

            if (!existingPost.getTituloPost().equals(postsComunidadDTO.getTituloPost()) && 
                postsComunidadRepository.existsByTituloPost(postsComunidadDTO.getTituloPost())) {
                throw new DuplicateResourceException("Ya existe un post con el título: " + postsComunidadDTO.getTituloPost());
            }

            if (!existingPost.getContenidoPost().equals(postsComunidadDTO.getContenidoPost()) && 
                postsComunidadRepository.existsByContenidoPost(postsComunidadDTO.getContenidoPost())) {
                throw new DuplicateResourceException("Ya existe un post con el mismo contenido");
            }

            existingPost.setTituloPost(postsComunidadDTO.getTituloPost());
            existingPost.setContenidoPost(postsComunidadDTO.getContenidoPost());
            existingPost.setImgPathComunidad(postsComunidadDTO.getImgPathComunidad());

            postsComunidadRepository.save(existingPost);
            return ResponseEntity.ok("Post actualizado correctamente");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DuplicateResourceException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el post: " + e.getMessage());
        }
    }

    public ResponseEntity<?> deletePost(Long id) {
        try {
            if (!postsComunidadRepository.existsById(id)) {
                throw new ResourceNotFoundException("Post no encontrado con id: " + id);
            }
            postsComunidadRepository.deleteById(id);
            return ResponseEntity.ok("Post eliminado correctamente");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el post: " + e.getMessage());
        }
    }

}
