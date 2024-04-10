package com.microservice.job.service;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.model.FilterJob;
import com.microservice.job.model.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {

     String createJob(Job job);
     String updateJob(Job job);
     String approveJob(Integer jobId);
     List<JobDTO> getAllJobs();
     JobDTO getJobById(Integer id);

     List<JobDTO> filteredJob(FilterJob filterJob);

     List<JobDTO> findJobByTitle(String title);
     List<Job> findJobByCompanyId(Integer id);

     String applyForJob(Integer candidateId,Integer jobId);

     List<Job> getAllJobsForAdmin();

}
