package com.practica.practica.domain;

import java.util.Date;

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
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_MATRICULA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TB_MATRICULA")
    @SequenceGenerator(name = "SQ_TB_MATRICULA", sequenceName = "SQ_TB_MATRICULA", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ESTUDIANTE")
    @JsonManagedReference
    private Estudiante estudiante; 

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CURSO")
    @JsonBackReference
    private Curso curso;

    @Column(name = "FECHA")
    private Date fecha;
}
