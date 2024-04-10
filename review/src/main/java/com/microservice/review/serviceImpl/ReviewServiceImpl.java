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
    public List<Review> getAllReviewByCompanyId(Integer id) {
        List<Review> reviews = this.reviewDao.findByCompanyId(id);
        return reviews;
    }

    @Override
    public Review getReviewById(Integer id) {
        Review review = this.reviewDao.findById(id).orElseThrow(() -> new ResourceNotFound("No review find with this id" + id));
        return review;
    }

    @Override
    public List<Review> getReviewByUserId(Integer id) {
        List<Review> byUserId = this.reviewDao.findByUserId(id);
        if (byUserId.isEmpty()){
            throw new ResourceNotFound("No reviews found with this user");
        }
        return byUserId;
    }

    @Override
    public Review updateReview(Review review) {
        Review review1 = this.reviewDao.findById(review.getId()).orElseThrow(() -> new ResourceNotFound("No review found to update with this id : " + review.getId()));
        Review save = this.reviewDao.save(review);
        return save;
    }
}
