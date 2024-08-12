package com.practica.practica.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Instructor;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long>{
    public List<Curso> findByInstructor(Instructor instructor);
}
