package com.practica.practica.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Matricula;
import com.practica.practica.repository.MatriculaRepository;
import com.practica.practica.service.CursoService;
import com.practica.practica.service.MatriculaService;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class MatriculaServiceImp implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final CursoService cursoService;
    

    public MatriculaServiceImp(MatriculaRepository matriculaRepository, CursoService cursoService) {
        this.matriculaRepository = matriculaRepository;
        this.cursoService = cursoService;
    }

    @Override
    public List<Matricula> findAll() {
        return (List<Matricula>) this.matriculaRepository.findAll();
    }

    @Override
    public Matricula findById(Long id) {
        Optional<Matricula> matriculaOp = this.matriculaRepository.findById(id);

        if (matriculaOp.isPresent()) {
            return matriculaOp.get();
        }
        return null;
    }

    @Override
    public Matricula update(Matricula matricula) {

        Optional<Matricula> matriculaOp = this.matriculaRepository.findById(matricula.getId());
        if (matriculaOp.isPresent()) {

            Matricula matriculaOriginal = matriculaOp.get();

            matriculaOriginal.setEstudiante(matricula.getEstudiante());
            matriculaOriginal.setCurso(matricula.getCurso());
            matriculaOriginal.setFecha(matricula.getFecha());

            return this.matriculaRepository.save(matriculaOriginal);
        }

        return null;
    }

    @Override
    public void deleteByID(Long id) {
        if (this.matriculaRepository.existsById(id)) {
            this.matriculaRepository.deleteById(id);
        }
    }

    @Override
    public Matricula save(Matricula matricula) {
        return this.matriculaRepository.save(matricula);
    }

    // --------------------------------------------------------------------------

    @Override
    public Matricula findByCursoAndEstudiante(Curso curso, Estudiante estudiante) {
        return this.matriculaRepository.findByCursoAndEstudiante(curso, estudiante);
    }
}