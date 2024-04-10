package com.microservice.company.DTO;

import lombok.Data;

import java.util.List;

@Data
public class CompanyDTO {
    private int id;
    private String companyName;
    private String description;
    private List<Review> reviews;

}
