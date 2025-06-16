package com.flymily.flymily.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flymily.flymily.dto.PostsComunidadDTO;
import com.flymily.flymily.service.PostsComunidadService;

@RestController
@RequestMapping("/api/posts-comunidad")
public class PostsComunidadController {

    private final PostsComunidadService postsComunidadService;

    public PostsComunidadController(PostsComunidadService postsComunidadService) {
        this.postsComunidadService = postsComunidadService;
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostsComunidadDTO postsComunidadDTO) {
        return postsComunidadService.createPost(postsComunidadDTO);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return postsComunidadService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return postsComunidadService.getPostById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostsComunidadDTO postsComunidadDTO) {
        return postsComunidadService.updatePost(id, postsComunidadDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        return postsComunidadService.deletePost(id);
    }
    
}
