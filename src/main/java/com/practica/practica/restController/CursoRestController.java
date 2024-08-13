package com.practica.practica.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Instructor;
import com.practica.practica.service.CursoService;
import com.practica.practica.service.InstructorService;

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
@RequestMapping(path = "/api/curso")
public class CursoRestController {

    private final CursoService cursoService;
    private final InstructorService instructorService;

    public CursoRestController(CursoService cursoService, InstructorService instructorService) {
        this.cursoService = cursoService;
        this.instructorService = instructorService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Curso>> getAll() {
        List<Curso> data = this.cursoService.findAll(); 
        return new ResponseEntity<List<Curso>>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody Curso curso, BindingResult result) {
        
        if (result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();
            
            result.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Curso data = this.cursoService.save(curso); 
        return new ResponseEntity<Curso>(data, HttpStatus.OK);
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody Curso curso, BindingResult result) {
        
        if (Objects.isNull(curso.getId())) {
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

        Curso data = this.cursoService.update(curso); 
        return new ResponseEntity<Curso>(data, HttpStatus.OK);
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Void> delete(@Param("id") Long id) {
        this.cursoService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{instructorId}/cursos")
    public List<Curso> getCursosByIntructor(@PathVariable Long instructorId) {
        Instructor instructor = this.instructorService.findById(instructorId);
        return this.cursoService.getCursosByIntructor(instructor);
    }

}
