package com.microservice.job.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDTO {
    private int id;
    private String title;
    private String description;
    private int minSalary;
    private int maxSalary;
    private String location;
    private Company company;
    private List<Review> reviews;
}
