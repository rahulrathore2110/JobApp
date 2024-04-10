package com.microservice.candidate.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Entity
@Data
public class PersonalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String headline;
    private LocalDate dateOfBirth;
    private String address;
    private String city;
    private String state;
    private String country;
    private boolean relocate;
    private String summary;
    @OneToOne
    @JsonBackReference
    private Candidate candidate;
}
