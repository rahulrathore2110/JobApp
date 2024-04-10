package com.microservice.job.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Entity
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private JobType jobType;
    private int minSalary;
    private int maxSalary;
    private SalaryType salaryType;
    private String location;
    private int noOfPositions;
    private Long recruiterNumber;
    private String recruiterEmail;
    private LocalDate startDate;
    private LocalDate endDate;
    private EducationLevel educationLevel;
    private JobStatus jobStatus;
    private Set<String> supplementPay = new HashSet<>();
    private Set<String> benefits= new HashSet<>();
    private Set<String> skills= new HashSet<>();
    private Set<Integer> candidatesApply= new HashSet<>();
    private int companyId;

}
