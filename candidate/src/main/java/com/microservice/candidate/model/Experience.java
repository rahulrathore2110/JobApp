package com.microservice.candidate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eid;
    private String title;
    private String employmentType;
    private String companyName;
    private String location;
    private String locationType;
    private LocalDate startDate;
    private LocalDate endDate;
    private String Description;
    @ManyToOne
    @JsonBackReference
    private Candidate candidate;
}
