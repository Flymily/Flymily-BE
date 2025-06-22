package com.flymily.flymily.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("(!) ERROR inesperado: " + ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(TituloYaExisteException.class)
    public ResponseEntity<String> handleTitutloYaExisteException(TituloYaExisteException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(EdadRangoNotFoundException.class)
    public ResponseEntity<String> handleEdadRangoNotFoundException(EdadRangoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<String> handleInvalidFilterException(InvalidFilterException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(LocalidadNotFoundException.class)
    public ResponseEntity<String> handleLocalidadNotFoundException(LocalidadNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(TipoViajeIdNotFoundException.class)
    public ResponseEntity<String> handleTipoViajeIdNotFoundException(TipoViajeIdNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(TipoViajeNotFoundException.class)
    public ResponseEntity<String> handleTipoViajeNotFoundException(TipoViajeNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("(!) ERROR: " + ex.getMessage());
    }

    @ExceptionHandler(ViajeNotFoundException.class)
    public ResponseEntity<String> handleViajeNotFoundException(ViajeNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
