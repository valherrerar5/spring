package com.practica.practica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Matricula;

@Service
public interface MatriculaService {

    public List<Matricula> findAll();
    
    public Matricula findById(Long id);

    public Matricula update(Matricula matricula);

    public void deleteByID(Long id);

    public Matricula save(Matricula matricula);

    // -------------------------------------------------------------------
    
    public Matricula findByCursoAndEstudiante(Curso curso, Estudiante estudiante);
    
}
