package com.practica.practica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Instructor;
import com.practica.practica.domain.Matricula;

@Service
public interface CursoService {

    public List<Curso> findAll();

    public Curso findById(Long id);

    public Curso update(Curso curso);

    public void deleteByID(Long id);

    public Curso save(Curso curso);

    public List<Estudiante> getEstudiantesByCurso(Long cursoId);

    public List<Curso> getCursosByIntructor(Instructor intructor);

    public List<Matricula> obtenerMatriculasPorCurso(Long cursoId);

}
