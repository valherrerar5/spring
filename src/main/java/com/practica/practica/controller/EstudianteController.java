package com.practica.practica.controller;

import java.util.List;

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
@RequestMapping(path = "/estudiante")
public class EstudianteController {

    private final CursoService cursoService;
    private final EstudianteService estudianteService;
     private final MatriculaService matriculaService;

    public EstudianteController(CursoService cursoService, EstudianteService estudianteService, MatriculaService matriculaService) {
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
        this.matriculaService = matriculaService;
    }

    @GetMapping("/")
    public String mostrarEstudaintes(Model model) {
        List<Estudiante> estudiantes = this.estudianteService.findAll();

        model.addAttribute("estudiantes", estudiantes);
        return "estudianteTemplate/listar";

    }

    @GetMapping("/editar/{estudianteId}")
    public String editarEstudiante(@PathVariable("estudianteId") Long estudianteId, Model model) {

        Estudiante estudiante = this.estudianteService.findById(estudianteId);
       // List<Curso> cursos = this.estudianteService.getCursosByEstudiante(estudianteId);
        
        model.addAttribute("estudiante", estudiante);
        //model.addAttribute("cursos", cursos);

        return "estudianteTemplate/edit";
    }

    @GetMapping("/crear/")
    public String crearEstudinte(Model model) {
        Estudiante estudiante = new Estudiante();
        model.addAttribute("estudiante", estudiante);

        return "estudianteTemplate/crear";
    }

    // -------------------------------------------

    @GetMapping("/eliminar/{estudianteId}")
    public String borrarEstudiante(@PathVariable("estudianteId") Long estudianteId) {
        this.estudianteService.deleteByID(estudianteId);
        return "redirect:/estudiante/";
    }

    @PostMapping("/editarEstudiante/{estudianteId}")
    public String actualizarEstudiante(@PathVariable("estudianteId") Long estudianteId,
            @Valid @ModelAttribute("estudiante") Estudiante estudiante,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // Si hay errores, vuelve a mostrar el formulario con los errores
            return "estudianteTemplate/edit";
        }

        if (estudiante.getId().equals(estudianteId)) {
            this.estudianteService.update(estudiante);
        }

        return "redirect:/estudiante/editar/{estudianteId}";
    }

    @PostMapping("/crearEstudiante/")
    public String crearEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            // Si hay errores, vuelve a mostrar el formulario con los errores
            return "estudianteTemplate/crear";
        }

        Estudiante estudianteNuevo = this.estudianteService.save(estudiante);
        return "redirect:/estudiante/editar/" + estudianteNuevo.getId();
    }

    @GetMapping("/eliminar/{cursoId}/estudiante/{estudianteId}")
    public String borrarEstudiante(@PathVariable("cursoId") Long cursoId, @PathVariable("estudianteId") Long estudianteId) {

        Estudiante estudiante = this.estudianteService.findById(estudianteId);
        Curso curso = this.cursoService.findById(cursoId);
        Matricula matricula = this.matriculaService.findByCursoAndEstudiante(curso, estudiante);

        this.matriculaService.deleteByID(matricula.getId());

        return "redirect:/estudiante/editar/" + estudianteId;
    }
}
