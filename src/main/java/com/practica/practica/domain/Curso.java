package com.practica.practica.domain;

import java.util.List;

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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(max = 100, message = "El nombre del curso no puede tener más de 100 caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "INSTRUCTOR")
    private Instructor instructor;

    @NotNull(message = "El curso se debe crear con creditos")
    @Min(value = 1, message = "La cantidad mínina de créditos debe ser al menos 1")
    @Max(value = 12, message = "El número de créditos no puede ser mayor a 12")
    @Column(name = "CREDITOS")
    private Long creditos;

    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonManagedReference
    private List<Matricula> matriculas;

    @Override
    public String toString() {
        return "Curso{id=" + id + ", nombre='" + nombre + "', creditos=" + creditos + "}";
    }
}
