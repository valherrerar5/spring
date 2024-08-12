package com.practica.practica.restController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.practica.domain.Matricula;
import com.practica.practica.service.MatriculaService;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(path = "/api/matricula")
public class MatriculaRestController {

    private final MatriculaService matriculaService;

    public MatriculaRestController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Matricula>> getAll() {
        List<Matricula> data = this.matriculaService.findAll(); 
        return new ResponseEntity<List<Matricula>>(data, HttpStatus.OK);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@Valid @RequestBody Matricula matricula, BindingResult result) {
        
        if (result.hasErrors()){
            HashMap<String, String> errors = new HashMap<>();
            
            result.getFieldErrors().forEach(error -> 
                errors.put(error.getField(), error.getDefaultMessage())
            );

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Matricula data = this.matriculaService.save(matricula); 
        return new ResponseEntity<Matricula>(data, HttpStatus.OK);
    }

    @PutMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@Valid @RequestBody Matricula matricula, BindingResult result) {
        
          if (Objects.isNull(matricula.getId())) {
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

        Matricula data = this.matriculaService.update(matricula); 
        return new ResponseEntity<Matricula>(data, HttpStatus.OK);
    }

    @DeleteMapping(path = "/")
    public ResponseEntity<Void> delete(@Param("id") Long id) {
        this.matriculaService.deleteByID(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}