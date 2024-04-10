package com.microservice.job.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterJob {
    private JobType jobType;
    private int minSalary;
    private int MaxSalary;
    private String location;
    private String skills;
}
