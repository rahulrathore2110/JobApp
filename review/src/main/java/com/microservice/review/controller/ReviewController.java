package com.microservice.review.controller;


import com.microservice.review.model.Review;
import com.microservice.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/")
    ResponseEntity<String> createReview(@RequestBody Review company){
        String review = this.reviewService.createReview(company);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/")
    ResponseEntity<List<Review>> getAllReview(){
         List<Review> reviews = this.reviewService.getAllReview();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/jobs/{id}")
    ResponseEntity<List<Review>> getAllReviewById(@PathVariable Integer id){
        List<Review> reviews = this.reviewService.getAllReviewById(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Review> getReviewById(@PathVariable Integer id){
        Review review = this.reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
