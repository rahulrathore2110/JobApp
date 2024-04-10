package com.microservice.job.controller;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.model.FilterJob;
import com.microservice.job.model.Job;
import com.microservice.job.service.CandidateClientService;
import com.microservice.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;


    @PostMapping("/")
    public ResponseEntity<String> creteJobHandler(@RequestBody Job job){
        String job1 = this.jobService.createJob(job);
        return new  ResponseEntity<>(job1, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<String> updateJobHandler(@RequestBody Job job){
        String job1 = this.jobService.updateJob(job);
        return new  ResponseEntity<>(job1, HttpStatus.ACCEPTED);
    }

    @GetMapping("/")
    public ResponseEntity<List<JobDTO>> getAllJobHandler(){
        List<JobDTO> allJobs = this.jobService.getAllJobs();
        return new  ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Job>> getAllJobAdminHandler(){
        List<Job> allJobs = this.jobService.getAllJobsForAdmin();
        return new  ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<JobDTO>> getAllFilterJobHandler(@RequestBody FilterJob filterJob){
        List<JobDTO> allJobs = this.jobService.filteredJob(filterJob);
        return new  ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobByIdHandler(@PathVariable Integer id){
        JobDTO job = this.jobService.getJobById(id);
        return new  ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/companyId/{id}")
    public ResponseEntity<List<Job>> getJobByCompanyIdHandler(@PathVariable Integer id){
        List<Job> job = this.jobService.findJobByCompanyId(id);
        return new  ResponseEntity<>(job, HttpStatus.OK);
    }

    @PutMapping("/approveJob/{id}")
    public ResponseEntity<String> approveJobHandler(@PathVariable Integer id){
        String job = this.jobService.approveJob(id);
        return new  ResponseEntity<>(job, HttpStatus.OK);
    }

    @GetMapping("/filterByTitle/{title}")
    public ResponseEntity<List<JobDTO>> getJobByTitleHandler(@PathVariable String title){
        List<JobDTO> jobs = this.jobService.findJobByTitle(title);
        return new  ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PutMapping("apply/{id}/{jobId}")
    public ResponseEntity<String> applyJob(@PathVariable Integer id,@PathVariable Integer jobId){
        String updateCandidate = this.jobService.applyForJob(id, jobId);
        return new ResponseEntity<>(updateCandidate, HttpStatus.ACCEPTED);
    }
}
