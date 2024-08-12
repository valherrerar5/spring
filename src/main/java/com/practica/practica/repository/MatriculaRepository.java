package com.practica.practica.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Matricula;

@Repository
public interface MatriculaRepository extends CrudRepository<Matricula, Long>{
    //public List<Matricula> findByCurso(Curso curso);

    public Matricula findByCursoAndEstudiante(Curso curso, Estudiante estudiante);

 
}
