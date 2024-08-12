package com.practica.practica.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CURSO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TB_CURSO")
    @SequenceGenerator(name = "SQ_TB_CURSO", sequenceName = "SQ_TB_CURSO", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INSTRUCTOR")
    // @JsonManagedReference
    private Instructor instructor;

    @Column(name = "CREDITOS")
    private Long creditos;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
    private List<Matricula> matriculas; // Relación con Matricula

     @Override
     public String toString() {
          return "Curso{id=" + id + ", nombre='" + nombre + "', creditos=" + creditos + "}";
     }
}
