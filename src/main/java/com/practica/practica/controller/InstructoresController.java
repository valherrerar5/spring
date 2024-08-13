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
import com.practica.practica.domain.Instructor;
import com.practica.practica.service.CursoService;
import com.practica.practica.service.InstructorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/instructor")
public class InstructoresController {

    private final CursoService cursoService;
    private final InstructorService instructorService;

    public InstructoresController(CursoService cursoService, InstructorService instructorService) {
        this.cursoService = cursoService;
        this.instructorService = instructorService;
    }

    @GetMapping("/")
    public String mostrarCursos(Model model) {
        List<Instructor> instructores = this.instructorService.findAll();

        model.addAttribute("instructores", instructores);
        return "instructorTemplate/listar";

    }

    @GetMapping("/editar/{instructorId}")
    public String editarCurso(@PathVariable("instructorId") Long instructorId, Model model) {
        Instructor instructor = this.instructorService.findById(instructorId);
        List<Curso> cursos = this.cursoService.getCursosByIntructor(instructor);

        model.addAttribute("instructor", instructor);
        model.addAttribute("cursos", cursos);

        return "instructorTemplate/edit";
    }

    @GetMapping("/crear/")
    public String crearCurso(Model model) {
        Instructor instructor = new Instructor();

        model.addAttribute("instructor", instructor);

        return "instructorTemplate/crear";
    }

    // -------------------------------------------

    @GetMapping("/eliminar/{instructorId}")
    public String borrarInstructor(@PathVariable("instructorId") Long instructorId) {

        Instructor instructor = this.instructorService.findById(instructorId);

        List<Curso> cursos = this.cursoService.getCursosByIntructor(instructor);

        //Si elimino un instructor debo quitar la relacion de los cursos
          for (Curso curso : cursos) {
            curso.setInstructor(null);
            this.cursoService.save(curso);
        }

        this.instructorService.deleteByID(instructorId);
        return "redirect:/instructor/";
    }

    @PostMapping("/editarInstructor/{instructorId}")
    public String actualizarCurso(@PathVariable("instructorId") Long instructorId,
            @Valid @ModelAttribute("instructor") Instructor instructor,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "instructorTemplate/edit";
        }

        if (instructor.getId().equals(instructorId)) {
            this.instructorService.update(instructor);
        }

        return "redirect:/instructor/editar/{instructorId}";
    }

    @PostMapping("/crearInstructor/")
    public String crearInstructor(@Valid @ModelAttribute("instructor") Instructor instructor,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "instructorTemplate/crear";
        }

        Instructor instructorNuevo = this.instructorService.save(instructor);
        return "redirect:/instructor/editar/" + instructorNuevo.getId();
    }

    @GetMapping("/eliminar/{cursoId}/instructor/{instructorId}")
    public String borrarInstructor(@PathVariable("cursoId") Long cursoId,
            @PathVariable("instructorId") Long instructorId) {

        Curso curso = this.cursoService.findById(cursoId);

        //Si elimina el instructor de un curso debo eliminar la relacion con curso 
        curso.setInstructor(null); 
        this.cursoService.save(curso); 
    
        return "redirect:/instructor/editar/" + instructorId;
    }
}
