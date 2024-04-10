package com.microservice.candidate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eduId;
    private String school;
    private String degree;
    private LocalDate startDate;
    private LocalDate endDate;
    private double grade;
    private String description;
    @ManyToOne
    @JsonBackReference
    private Candidate candidate;

}
