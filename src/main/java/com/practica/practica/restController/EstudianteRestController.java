package com.practica.practica.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.service.EstudianteService;

import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/estudiante")
public class EstudianteRestController {

    private final EstudianteService estudianteService;

    public EstudianteRestController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Estudiante>> getAll() {
        List<Estudiante> data = this.estudianteService.findAll(); 
        return new ResponseEntity<List<Estudiante>>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody Estudiante estudiante, BindingResult result) {
        
        if (result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();
            
            result.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Estudiante data = this.estudianteService.save(estudiante); 
        return new ResponseEntity<Estudiante>(data, HttpStatus.OK);
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody Estudiante estudiante, BindingResult result) {
        
          if (Objects.isNull(estudiante.getId())) {
            HashMap<String, String> errors = new HashMap<>();
            errors.put("error", "La identificación no puede ser nula");
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        if (result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();

            result.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Estudiante data = this.estudianteService.update(estudiante); 
        return new ResponseEntity<Estudiante>(data, HttpStatus.OK);
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Void> delete(@Param("id") Long id) {
        this.estudianteService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @GetMapping("/{estudianteId}/cursos")
    // public List<Curso> getEstudiantesByCurso(@PathVariable Long estudianteId) {
    //     return estudianteService.getCursosByEstudiante(estudianteId);
    // }
}
