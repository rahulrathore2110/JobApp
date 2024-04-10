package com.microservice.candidate.repository;

import com.microservice.candidate.model.Experience;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceDao extends JpaRepository<Experience,Integer> {
}
