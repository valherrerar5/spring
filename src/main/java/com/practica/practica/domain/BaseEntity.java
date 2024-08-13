package com.practica.practica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Debe ingresar un nombre")
    @Size(max = 100, message = "El nombre no puede tener más de 100 caracteres")
    @Column(name = "NOMBRE")
    private String nombre;

    @NotBlank(message = "Debe ingresar un apellido")
    @Size(max = 100, message = "El apellido no puede tener más de 100 caracteres")
    @Column(name = "APELLIDO")
    private String apellido;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + id + ", nombre='" + nombre + "', apellido='" + apellido + "'}";
    }
}
