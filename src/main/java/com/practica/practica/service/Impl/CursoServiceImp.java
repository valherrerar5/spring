package com.practica.practica.service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Instructor;
import com.practica.practica.domain.Matricula;
import com.practica.practica.repository.CursoRepository;
import com.practica.practica.service.CursoService;

@Service
@Transactional
public class CursoServiceImp implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImp(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> findAll() {
        return (List<Curso>) this.cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        Optional<Curso> cursoOp = this.cursoRepository.findById(id);
        if (cursoOp.isPresent()) {
            return cursoOp.get();
        }
        return null;
    }

    @Override
    public Curso update(Curso curso) {

        Optional<Curso> cursoOp = this.cursoRepository.findById(curso.getId());
        if (cursoOp.isPresent()) {

            Curso cursoOriginal = cursoOp.get();

            cursoOriginal.setNombre(curso.getNombre());
            cursoOriginal.setInstructor(curso.getInstructor());
            cursoOriginal.setCreditos(curso.getCreditos());

            return this.cursoRepository.save(cursoOriginal);
        }

        return null;
    }

    @Override
    public void deleteByID(Long id) {
        if (this.cursoRepository.existsById(id)) {
            this.cursoRepository.deleteById(id);
        }
    }

    @Override
    public Curso save(Curso curso) {

        Curso notaNueva = this.cursoRepository.save(curso);

        return notaNueva;
    }

    @Override
    public List<Estudiante> getEstudiantesByCurso(Long cursoId) {
        
        Optional<Curso> cursoOp = cursoRepository.findById(cursoId);

        if (cursoOp.isPresent()) {
            Curso curso = cursoOp.get();

            return curso.getMatriculas().stream()
                    .map(Matricula::getEstudiante)
                    .collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public List<Curso> getCursosByIntructor(Instructor intructor) {
        return (List<Curso>) this.cursoRepository.findByInstructor(intructor);
    }

    @Override
    public List<Matricula> obtenerMatriculasPorCurso(Long cursoId) {
        Optional<Curso> cursoOp = cursoRepository.findById(cursoId);

        if(cursoOp.isPresent()){
            Curso curso = cursoOp.get();   
            return curso.getMatriculas();
        }

        return null;
    }
}