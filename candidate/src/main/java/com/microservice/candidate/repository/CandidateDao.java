package com.microservice.candidate.repository;

import com.microservice.candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDao extends JpaRepository<Candidate, Integer> {
}
