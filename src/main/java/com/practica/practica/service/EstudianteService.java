package com.practica.practica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;


@Service
public interface EstudianteService {

    public List<Estudiante> findAll();
    
    public Estudiante findById(Long id);

    public Estudiante update(Estudiante estudiante);

    public void deleteByID(Long id);

    public Estudiante save(Estudiante estudiante);

    //public List<Curso> getCursosByEstudiante(Long estudianteId);
}
