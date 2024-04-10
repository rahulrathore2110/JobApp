package com.microservice.candidate.repository;

import com.microservice.candidate.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationDao extends JpaRepository<Certification,Integer> {
}
