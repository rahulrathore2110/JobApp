package com.microservice.candidate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cid;
    private String certificateName;
    private String organisation;
    private LocalDate issueDate;
    private String certificateId;
    private String certificateUrl;
    @ManyToOne
    @JsonBackReference
    private Candidate candidate;
}
