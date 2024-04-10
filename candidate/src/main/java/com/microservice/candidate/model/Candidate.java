package com.microservice.candidate.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Long number;
    private String email;
    private HashSet<String> skills = new HashSet<>();
    private HashSet<String> languages = new HashSet<>();

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "candidate")
    private PersonalDetails personalDetails;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Education> educations;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Experience> experiences;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "candidate")
    private List<Certification> certifications;
    private Set<Integer> allApplyJobs = new HashSet<>();

}
