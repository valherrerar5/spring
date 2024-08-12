package com.practica.practica.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Instructor;


@Service
public interface InstructorService {

    public List<Instructor> findAll();
    
    public Instructor findById(Long id);

    public Instructor update(Instructor instructor);

    public void deleteByID(Long id);

    public Instructor save(Instructor instructor);
}
