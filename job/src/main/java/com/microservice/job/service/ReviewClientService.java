package com.microservice.job.service;

import com.microservice.job.DTO.Company;
import com.microservice.job.DTO.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="REVIEW-SERVICE")
public interface ReviewClientService {

    @GetMapping("/reviews/jobs/{id}")
    List<Review> getAllReview(@PathVariable Integer id);
}
