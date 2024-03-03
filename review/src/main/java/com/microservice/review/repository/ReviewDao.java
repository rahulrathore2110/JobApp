package com.microservice.review.repository;


import com.microservice.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewDao extends JpaRepository<Review,Integer> {

    List<Review> findByJobId(Integer id);
}
