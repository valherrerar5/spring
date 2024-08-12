package com.practica.practica.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.practica.domain.Estudiante;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Long>{
    
}
