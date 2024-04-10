package com.microservice.candidate.repository;

import com.microservice.candidate.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationDao extends JpaRepository<Education,Integer> {
}
