package com.practica.practica.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practica.practica.domain.Instructor;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long> {
}
