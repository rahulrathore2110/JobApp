package com.microservice.company.service;

import com.microservice.company.DTO.Review;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClientService {

    @GetMapping("/reviews/company/{id}")
    List<Review> getAllReviewByCompanyId(@PathVariable Integer id);
}
