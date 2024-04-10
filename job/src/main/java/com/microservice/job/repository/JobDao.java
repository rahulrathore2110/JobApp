package com.microservice.job.repository;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobDao extends JpaRepository<Job, Integer> {
    List<Job> findByTitleContaining(String title);
    List<Job> findByCompanyId(Integer id);
}
