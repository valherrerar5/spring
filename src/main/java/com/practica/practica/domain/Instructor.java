package com.practica.practica.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_INSTRUCTOR")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instructor  extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_TB_INSTRUCTOR")
    @SequenceGenerator(name = "SQ_TB_INSTRUCTOR", sequenceName = "SQ_TB_INSTRUCTOR", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
}
