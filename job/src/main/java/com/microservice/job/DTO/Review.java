package com.microservice.job.DTO;

import lombok.Data;

@Data
public class Review {
    private int id;
    private String title;
    private String description;
    private double rating;
    private int userId;


}
