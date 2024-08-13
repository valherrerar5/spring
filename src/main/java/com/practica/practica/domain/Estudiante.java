package com.practica.practica.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_ESTUDIANTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiante extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TB_ESTUDIANTE")
    @SequenceGenerator(name = "SQ_TB_ESTUDIANTE", sequenceName = "SQ_TB_ESTUDIANTE", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    // @NotBlank(message = "Debe ingresar un nombre")
    // @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    // @Column(name = "NOMBRE")
    // private String nombre;

    // @NotBlank(message = "Debe ingresar un apellido")
    // @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    // @Column(name = "APELLIDO")
    // private String apellido;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonBackReference
    private List<Matricula> matriculas; // Relación con Matricula

    //  @Override
    //  public String toString() {
    //      return "Estudiante{id=" + id + ", nombre='" + nombre + "', apellido='" + apellido + "'}";
    //  }
}