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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, orphanRemoval = false)
    @JsonBackReference
    private List<Matricula> matriculas; 
}