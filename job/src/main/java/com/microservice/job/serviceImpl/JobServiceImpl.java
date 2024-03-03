package com.microservice.job.serviceImpl;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.DTO.Review;
import com.microservice.job.Exception.ResourceNotFound;
import com.microservice.job.DTO.Company;
import com.microservice.job.model.Job;
import com.microservice.job.repository.JobDao;
import com.microservice.job.service.CompanyClientService;
import com.microservice.job.service.JobService;
import com.microservice.job.service.ReviewClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CompanyClientService companyClientService;

    @Autowired
    private ReviewClientService reviewClientService;



    @Override
    public String createJob(Job job) {
        Job save = jobDao.save(job);
        return "Job Saved";
    }

    @Override
    public List<JobDTO> getAllJobs() {

        List<Job> jobs = this.jobDao.findAll();

        List<JobDTO> dtos = new ArrayList<>();

        for(Job job:jobs){
            Company company = companyClientService.getCompany(job.getCompanyId());
            List<Review> allReview = reviewClientService.getAllReview(job.getId());
            JobDTO jobDTO = jobToJobDto(job, company,allReview);
            dtos.add(jobDTO);
        }

        return dtos;
    }

    @Override
    public JobDTO getJobById(Integer id) {
        Job job = this.jobDao.findById(id).orElseThrow(() -> new ResourceNotFound("No jon found with this id : " + id));
        Company company = companyClientService.getCompany(job.getCompanyId());
        List<Review> allReview = reviewClientService.getAllReview(job.getId());
        JobDTO jobDTO = jobToJobDto(job, company,allReview);
        return jobDTO;
    }

    public JobDTO jobToJobDto(Job job, Company company, List<Review> review){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(review);

        return jobDTO;
    }
}
