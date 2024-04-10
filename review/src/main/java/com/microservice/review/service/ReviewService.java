package com.microservice.review.service;


import com.microservice.review.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {

    String createReview(Review company);
    List<Review> getAllReview();

    List<Review> getAllReviewByCompanyId(Integer id);

    Review getReviewById(Integer id);
    List<Review> getReviewByUserId(Integer id);

    Review updateReview(Review review);
}
