package com.microservice.job.service;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

     String createJob(Job job);
     List<JobDTO> getAllJobs();
     JobDTO getJobById(Integer id);


}
