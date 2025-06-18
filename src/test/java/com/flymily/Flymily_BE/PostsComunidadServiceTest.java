package com.flymily.Flymily_BE;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.flymily.flymily.dto.PostsComunidadDTO;
import com.flymily.flymily.exceptions.DuplicateResourceException;
import com.flymily.flymily.exceptions.ResourceNotFoundException;
import com.flymily.flymily.model.PostsComunidad;
import com.flymily.flymily.repository.PostsComunidadRepository;
import com.flymily.flymily.service.PostsComunidadService;

@ExtendWith(MockitoExtension.class)
public class PostsComunidadServiceTest {

    @Mock
    private PostsComunidadRepository postsComunidadRepository;

    @InjectMocks
    private PostsComunidadService postsComunidadService;

    private PostsComunidad post;
    private PostsComunidadDTO postDTO;

    @BeforeEach
    public void setUp() {
        post = new PostsComunidad();
        post.setId(1L);
        post.setTituloPost("Título del post");
        post.setContenidoPost("Contenido del post");
        post.setImgPathComunidad("imagen.jpg");

        postDTO = new PostsComunidadDTO();
        postDTO.setTituloPost("Título del post");
        postDTO.setContenidoPost("Contenido del post");
        postDTO.setImgPathComunidad("imagen.jpg");
    }

    @Test
    void whenCreatePost_thenReturnCreatedPost() {
        when(postsComunidadRepository.existsByTituloPost(anyString())).thenReturn(false);
        when(postsComunidadRepository.existsByContenidoPost(anyString())).thenReturn(false);
        when(postsComunidadRepository.save(any(PostsComunidad.class))).thenReturn(post);

        ResponseEntity<?> response = postsComunidadService.createPost(postDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof PostsComunidad);
    }

    @Test
    void whenCreatePostWithDuplicateTitle_thenThrowException() {
        when(postsComunidadRepository.existsByTituloPost(anyString())).thenReturn(true);

        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class, () -> {
            postsComunidadService.createPost(postDTO);
        });

        assertEquals("Ya existe un post con el título: " + postDTO.getTituloPost(), exception.getMessage());
    }

    @Test
    void whenGetAllPosts_thenReturnPostList() {
        when(postsComunidadRepository.findAll()).thenReturn(List.of(post));

        ResponseEntity<?> response = postsComunidadService.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof List);
    }

    @Test
    void whenGetPostById_thenReturnPost() {
        when(postsComunidadRepository.findById(1L)).thenReturn(Optional.of(post));

        ResponseEntity<?> response = postsComunidadService.getPostById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof PostsComunidad);
    }

    @Test
    void whenGetPostByInvalidId_thenThrowException() {
        when(postsComunidadRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            postsComunidadService.getPostById(2L);
        });

        assertEquals("Post no encontrado con id: 2", exception.getMessage());
    }

    @Test
    void whenUpdatePost_thenReturnUpdatedPost() {
        when(postsComunidadRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postsComunidadRepository.existsByTituloPost(anyString())).thenReturn(false);
        when(postsComunidadRepository.existsByContenidoPost(anyString())).thenReturn(false);
        when(postsComunidadRepository.save(any(PostsComunidad.class))).thenReturn(post);

        ResponseEntity<?> response = postsComunidadService.updatePost(1L, postDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post actualizado correctamente", response.getBody());
    }

    @Test
    void whenDeletePost_thenReturnSuccessMessage() {
        when(postsComunidadRepository.existsById(1L)).thenReturn(true);
        doNothing().when(postsComunidadRepository).deleteById(1L);

        ResponseEntity<?> response = postsComunidadService.deletePost(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post eliminado correctamente", response.getBody());
    }
}