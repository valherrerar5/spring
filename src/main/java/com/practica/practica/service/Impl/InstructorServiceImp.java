package com.practica.practica.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Instructor;
import com.practica.practica.repository.InstructorRepository;
import com.practica.practica.service.InstructorService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class InstructorServiceImp implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorServiceImp(InstructorRepository instructorRepository){
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> findAll() {

        return (List<Instructor>) this.instructorRepository.findAll();
       
    }

    @Override
    public Instructor findById(Long id) {

        Optional<Instructor> estudianteOp = this.instructorRepository.findById(id);
        
        if (estudianteOp.isPresent()) {
            return estudianteOp.get();
        }
        return null;
    }

    @Override
    public Instructor update(Instructor instructor) {

        Optional<Instructor> instructorOp = this.instructorRepository.findById(instructor.getId());
        if (instructorOp.isPresent()) {

            Instructor instructorOriginal = instructorOp.get();

            instructorOriginal.setNombre(instructor.getNombre());
            instructorOriginal.setApellido(instructor.getApellido());
            

            return this.instructorRepository.save(instructorOriginal);
        }

        return null;
    }

    @Override
    public void deleteByID(Long id) {
        System.out.println("id --------------------" + id);
        if (this.instructorRepository.existsById(id)) {
            this.instructorRepository.deleteById(id);
        }
    }

    @Override
    public Instructor save(Instructor instructor) {
        return this.instructorRepository.save(instructor);
    }
}
