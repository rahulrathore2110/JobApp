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
    ResponseEntity<String> createReview(@RequestBody Review review){
        String review1 = this.reviewService.createReview(review);
        return new ResponseEntity<>(review1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    ResponseEntity<Review> updateReview(@RequestBody Review review){
        Review review1 = this.reviewService.updateReview(review);
        return new ResponseEntity<>(review1, HttpStatus.ACCEPTED);
    }
    @GetMapping("/")
    ResponseEntity<List<Review>> getAllReview(){
         List<Review> reviews = this.reviewService.getAllReview();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/company/{id}")
    ResponseEntity<List<Review>> getAllReviewByCompanyId(@PathVariable Integer id){
        List<Review> reviews = this.reviewService.getAllReviewByCompanyId(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    ResponseEntity<List<Review>> getAllReviewByUserId(@PathVariable Integer id){
        List<Review> reviews = this.reviewService.getReviewByUserId(id);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    ResponseEntity<Review> getReviewById(@PathVariable Integer id){
        Review review = this.reviewService.getReviewById(id);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }
}
