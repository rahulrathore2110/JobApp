package com.microservice.review.serviceImpl;

import com.microservice.review.Exception.ResourceNotFound;
import com.microservice.review.model.Review;
import com.microservice.review.repository.ReviewDao;
import com.microservice.review.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;
    @Override
    public String createReview(Review review) {
        Review review1 = this.reviewDao.save(review);
        return "Review Create Successfully";

    }

    @Override
    public List<Review> getAllReview() {
        List<Review> reviews = this.reviewDao.findAll();
        return reviews;
    }

    @Override
    public List<Review> getAllReviewById(Integer id) {
        List<Review> byJobId = this.reviewDao.findByJobId(id);
        return byJobId;
    }

    @Override
    public Review getReviewById(Integer id) {
        Review review = this.reviewDao.findById(id).orElseThrow(() -> new ResourceNotFound("No review find with this id" + id));
        return review;
    }
}
