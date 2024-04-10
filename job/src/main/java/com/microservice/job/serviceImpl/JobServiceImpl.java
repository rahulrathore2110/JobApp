package com.microservice.job.serviceImpl;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.DTO.Review;
import com.microservice.job.Exception.ResourceNotFound;
import com.microservice.job.DTO.Company;
import com.microservice.job.model.FilterJob;
import com.microservice.job.model.Job;
import com.microservice.job.model.JobStatus;
import com.microservice.job.repository.JobDao;
import com.microservice.job.service.CandidateClientService;
import com.microservice.job.service.CompanyClientService;
import com.microservice.job.service.JobService;
import com.microservice.job.service.ReviewClientService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private CompanyClientService companyClientService;

    @Autowired
    private ReviewClientService reviewClientService;

    @Autowired
    private CandidateClientService candidateClientService;



    public JobDTO ServerDownMessage1(Exception e){
    throw new ResourceNotFound("We are not able to Fetch any data at this time. please try after some time");
    }

    public List<JobDTO> ServerDownMessage2(Exception e){
        throw new ResourceNotFound("We are not able to Fetch any data at this time. please try after some time");
    }
    @Override
    public String createJob(Job job) {
        job.setJobStatus(JobStatus.REVIEW);
        Job save = jobDao.save(job);
        return "Job Saved";
    }

    @Override
    public String updateJob(Job job) {
        Job job1 = this.jobDao.findById(job.getId()).orElseThrow(() -> new ResourceNotFound("No job found with this id : " + job.getId()));

        job1.setId(job.getId());
        job1.setTitle(job.getTitle());
        job1.setDescription(job.getDescription());
        job1.setJobType(job.getJobType());
        job1.setMinSalary(job.getMinSalary());
        job1.setMaxSalary(job.getMaxSalary());
        job1.setSalaryType(job.getSalaryType());
        job1.setLocation(job.getLocation());
        job1.setNoOfPositions(job.getNoOfPositions());
        job1.setRecruiterNumber(job.getRecruiterNumber());
        job1.setRecruiterEmail(job.getRecruiterEmail());
        job1.setStartDate(job.getStartDate());
        job1.setEndDate(job.getEndDate());
        job1.setEducationLevel(job.getEducationLevel());
        job1.setJobStatus(job.getJobStatus());
        job1.setSupplementPay(job.getSupplementPay());
        job1.setBenefits(job.getBenefits());
        job1.setSkills(job.getSkills());
        job1.setCompanyId(job.getCompanyId());

        this.jobDao.save(job1);

        return "Your job update successfully";
    }

    @Override
    public String approveJob(Integer jobId) {
        Job job = this.jobDao.findById(jobId).orElseThrow(() -> new ResourceNotFound("No jon found with this id : " + jobId));
        if (job.getJobStatus().equals(JobStatus.RUNNING)){
            throw new ResourceNotFound("Job Already Approved");
        }
        job.setJobStatus(JobStatus.RUNNING);
        this.jobDao.save(job);
        return "Job approved successfully";
    }

    @Override
    @CircuitBreaker(name = "serviceBreaker",fallbackMethod = "ServerDownMessage2")
    public List<JobDTO> getAllJobs() {

        List<Job> jobs = this.jobDao.findAll();

        List<JobDTO> dtos = new ArrayList<>();

        for(Job job:jobs){
            if (job.getJobStatus().equals(JobStatus.RUNNING)){
                Company company = companyClientService.getCompany(job.getCompanyId());
                JobDTO jobDTO = jobToJobDto(job, company);
                dtos.add(jobDTO);
            }

        }

        return dtos;
    }

    @Override
    @CircuitBreaker(name = "serviceBreaker",fallbackMethod = "ServerDownMessage1")
    public JobDTO getJobById(Integer id) {
        Job job = this.jobDao.findById(id).orElseThrow(() -> new ResourceNotFound("No job found with this id : " + id));
        if (job.getJobStatus().equals(JobStatus.RUNNING)){
            Company company = companyClientService.getCompany(job.getCompanyId());
            JobDTO jobDTO = jobToJobDto(job, company);
            return jobDTO;
        }

        throw new ResourceNotFound("No job found with this Id : " + id);
    }

    @Override
    @CircuitBreaker(name = "serviceBreaker",fallbackMethod = "ServerDownMessage2")
    public List<JobDTO> filteredJob(FilterJob filterJob) {
        List<Job> jobs = this.jobDao.findAll();

        List<JobDTO> filterJobs = new ArrayList<>();
        System.out.println("Inside");
        for (Job job:jobs){
            System.out.println("Inside1");

            
            if ((job.getJobType().equals(filterJob.getJobType()) && job.getMinSalary() >= filterJob.getMinSalary() && job.getMaxSalary() <= filterJob.getMaxSalary() && job.getLocation().equals(filterJob.getLocation()) && job.getSkills().contains(filterJob.getSkills())) && job.getJobStatus().equals(JobStatus.RUNNING)){
                System.out.println("Inside loop");
                Company company = companyClientService.getCompany(job.getCompanyId());
                JobDTO jobDTO = jobToJobDto(job, company);
                filterJobs.add(jobDTO);
            }
        }
        System.out.println("complete");
        return filterJobs;
    }

    @Override
    @CircuitBreaker(name = "serviceBreaker",fallbackMethod = "ServerDownMessage2")
    public List<JobDTO> findJobByTitle(String title) {
        List<Job> jobs = this.jobDao.findByTitleContaining(title);
        List<JobDTO> filterJobs = new ArrayList<>();
        for (Job job:jobs){
            if (job.getJobStatus().equals(JobStatus.RUNNING)){
                Company company = companyClientService.getCompany(job.getCompanyId());
                JobDTO jobDTO = jobToJobDto(job, company);
                filterJobs.add(jobDTO);
            }
        }
        return filterJobs;
    }

    @Override
    public List<Job> findJobByCompanyId(Integer id) {
        List<Job> jobByCompanyId = this.jobDao.findByCompanyId(id);
        if (jobByCompanyId.isEmpty()){
            throw new ResourceNotFound("No jon found with this Id : "+id);
        }
        return jobByCompanyId;
    }

    @Override
    public String applyForJob(Integer candidateId, Integer jobId) {
        Job job = this.jobDao.findById(jobId).orElseThrow(() -> new ResourceNotFound("No job found with this id : " + jobId));

        if (!job.getJobStatus().equals(JobStatus.RUNNING)){
            throw new ResourceNotFound("No job found with this id");
        }

        String apply = this.candidateClientService.applyForJob(candidateId, jobId);

        Set<Integer> candidatesApply = job.getCandidatesApply();

        if (candidatesApply != null){
            candidatesApply.add(candidateId);
            job.setCandidatesApply(candidatesApply);
        }else {
            job.setCandidatesApply(new HashSet<>(candidateId));
        }

        Job save = this.jobDao.save(job);
        return "Job apply successfully";
    }

    @Override
    public List<Job> getAllJobsForAdmin() {
        List<Job> jobs = this.jobDao.findAll();
        if (jobs.isEmpty()){
            throw new ResourceNotFound("No job found");
        }

        return jobs;
    }

    public JobDTO jobToJobDto(Job job, Company company){
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setJobType(job.getJobType());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setSalaryType(job.getSalaryType());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setNoOfPositions(job.getNoOfPositions());
        jobDTO.setRecruiterNumber(job.getRecruiterNumber());
        jobDTO.setRecruiterEmail(job.getRecruiterEmail());
        jobDTO.setStartDate(job.getStartDate());
        jobDTO.setEndDate(job.getEndDate());
        jobDTO.setEducationLevel(job.getEducationLevel());
        jobDTO.setJobStatus(job.getJobStatus());
        jobDTO.setSupplementPay(job.getSupplementPay());
        jobDTO.setBenefits(job.getBenefits());
        jobDTO.setSkills(job.getSkills());
        jobDTO.setCandidatesApply(job.getCandidatesApply());
        jobDTO.setCompany(company);
        return jobDTO;
    }
}
