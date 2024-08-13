package com.practica.practica.controller;

import java.util.List;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Matricula;
import com.practica.practica.service.CursoService;
import com.practica.practica.service.EstudianteService;
import com.practica.practica.service.MatriculaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/matricula")
public class MatriculaController {
    
    private final CursoService cursoService;
    private final EstudianteService estudianteService;
    private final MatriculaService matriculaService;

    public MatriculaController(CursoService cursoService, EstudianteService estudianteService, MatriculaService matriculaService) {
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
        this.matriculaService = matriculaService;
    }

    @GetMapping("/{cursoId}")
    public String mostrarCursos(@PathVariable("cursoId") Long cursoId, Model model){
        
        Curso curso = this.cursoService.findById(cursoId);
        List<Estudiante> estudiantes = this.estudianteService.findAll();

        Matricula matricula = new Matricula();

        model.addAttribute("curso", curso);
        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("matricula", matricula);
        

        return "matriculaTemplate/crear";

    }

    // -----------------------------------------------------------------------------------
    
    @PostMapping("/{cursoId}")
    public String actualizarCurso(@PathVariable("cursoId") Long cursoId, @Valid @ModelAttribute("matricula") Matricula matricula, Model model) {

        Curso curso = this.cursoService.findById(cursoId);

        matricula.setCurso(curso);
        Date fecha = new Date();
        matricula.setFecha(fecha);

        this.matriculaService.save(matricula);

        return "redirect:/curso/detalle/" + curso.getId();
    }
}
