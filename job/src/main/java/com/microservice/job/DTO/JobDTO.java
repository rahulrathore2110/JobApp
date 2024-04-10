package com.microservice.job.DTO;

import com.microservice.job.model.EducationLevel;
import com.microservice.job.model.JobStatus;
import com.microservice.job.model.JobType;
import com.microservice.job.model.SalaryType;
import lombok.Data;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class JobDTO {
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
    private Company company;
}
