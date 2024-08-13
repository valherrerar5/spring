package com.practica.practica.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import com.practica.practica.domain.Curso;
import com.practica.practica.domain.Estudiante;
import com.practica.practica.domain.Instructor;
import com.practica.practica.domain.Matricula;
import com.practica.practica.service.CursoService;
import com.practica.practica.service.EstudianteService;
import com.practica.practica.service.InstructorService;
import com.practica.practica.service.MatriculaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/curso")
public class CursoController {
    
    private final CursoService cursoService;
    private final InstructorService instructorService;
    private final EstudianteService estudianteService;
    private final MatriculaService matriculaService;

    public CursoController(CursoService cursoService, InstructorService instructorService, EstudianteService estudianteService, MatriculaService matriculaService){
        this.cursoService = cursoService;
        this.instructorService = instructorService;
        this.estudianteService= estudianteService;
        this.matriculaService = matriculaService;
    }

    @GetMapping("/")
    public String mostrarCursos(Model model){
        
        List<Curso> cursos = this.cursoService.findAll();

        model.addAttribute("cursos", cursos);

        return "cursoTemplate/listar";

    }

    @GetMapping("/editar/{cursoId}")
    public String editarCurso(@PathVariable("cursoId") Long cursoId, Model model) {
        Curso curso = cursoService.findById(cursoId);

        List<Instructor> instructores = instructorService.findAll();

        model.addAttribute("curso", curso);
        model.addAttribute("instructores", instructores);

        return "cursoTemplate/edit";
    }

    @GetMapping("/crear/")
    public String crearCurso(Model model) {
        System.out.println("LLega");
        Curso curso = new Curso();
        List<Instructor> instructores = instructorService.findAll();

        model.addAttribute("curso", curso);
        model.addAttribute("instructores", instructores);

        return "cursoTemplate/crear";
    }

    @GetMapping("/detalle/{cursoId}")
    public String cursoDetalle(@PathVariable("cursoId") Long cursoId, Model model) {
        Curso curso = cursoService.findById(cursoId);
        List<Estudiante> estudiantes = this.cursoService.getEstudiantesByCurso(cursoId);

        model.addAttribute("curso", curso);
        model.addAttribute("estudiantes", estudiantes);

        return "cursoTemplate/detalle";
    }

    // -------------------------------------------

    @GetMapping("/eliminar/{cursoId}")
    public String borrarCurso(@PathVariable("cursoId") Long cursoId) {

        List<Matricula> matriculas = this.cursoService.obtenerMatriculasPorCurso(cursoId);

        for (Matricula matricula : matriculas) {
            this.matriculaService.deleteByID(matricula.getId());
        }
    
        this.cursoService.deleteByID(cursoId);
        return "redirect:/curso/";
    }

    @PostMapping("/editarCurso/{cursoId}")
    public String actualizarCurso(@PathVariable("cursoId") Long cursoId, @Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult, Model model) {

        
        if (bindingResult.hasErrors()) {
          
            return "cursoTemplate/edit";
        }

        if (curso.getId().equals(cursoId)){
            this.cursoService.update(curso);
        }

        return "redirect:/curso/editar/{cursoId}";
    }

    @PostMapping("/crearCurso/")
    public String crearCurso(@Valid @ModelAttribute("curso") Curso curso, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {

            return "cursoTemplate/crear";
        }

        Curso cursoNuevo = this.cursoService.save(curso);
        return "redirect:/curso/editar/" + cursoNuevo.getId();
    }

    @GetMapping("/eliminar/{cursoId}/estudiante/{estudianteId}")
    public String borrarEstudiante(@PathVariable("cursoId") Long cursoId, @PathVariable("estudianteId") Long estudianteId) {

        Estudiante estudiante = this.estudianteService.findById(estudianteId);
        Curso curso = this.cursoService.findById(cursoId);
        Matricula matricula = this.matriculaService.findByCursoAndEstudiante(curso, estudiante);

        this.matriculaService.deleteByID(matricula.getId());

        return "redirect:/curso/detalle/" + cursoId;
    }
}
