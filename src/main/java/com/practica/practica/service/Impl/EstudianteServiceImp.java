package com.practica.practica.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Matricula;
import com.practica.practica.repository.EstudianteRepository;
import com.practica.practica.service.EstudianteService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstudianteServiceImp implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteServiceImp(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    @Override
    public List<Estudiante> findAll() {
        return (List<Estudiante>) this.estudianteRepository.findAll();
    }

    @Override
    public Estudiante findById(Long id) {
        Optional<Estudiante> estudianteOp = this.estudianteRepository.findById(id);
        if (estudianteOp.isPresent()) {
            return estudianteOp.get();
        }
        return null;
    }

    @Override
    public Estudiante update(Estudiante estudiante) {

        Optional<Estudiante> notaOp = this.estudianteRepository.findById(estudiante.getId());
        if (notaOp.isPresent()) {

            Estudiante notaOriginal = notaOp.get();

            notaOriginal.setNombre(estudiante.getNombre());
            notaOriginal.setApellido(estudiante.getApellido());

            return this.estudianteRepository.save(notaOriginal);
        }

        return null;
    }

    @Override
    public void deleteByID(Long id) {
        if (this.estudianteRepository.existsById(id)) {
            this.estudianteRepository.deleteById(id);
        }
    }

    @Override
    public Estudiante save(Estudiante estudiante) {
        return this.estudianteRepository.save(estudiante);
    }

    @Override
    public List<Curso> getCursosByEstudiante(Long estudianteId) {
        Optional<Estudiante> estudianteOp = estudianteRepository.findById(estudianteId);
            
        if(estudianteOp.isPresent()){

            Estudiante estudiante = estudianteOp.get();
            
            return estudiante.getMatriculas().stream()
                .map(Matricula::getCurso)
                .collect(Collectors.toList());
        }    

        return null;
    }
}