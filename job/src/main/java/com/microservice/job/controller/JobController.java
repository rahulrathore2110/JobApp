package com.microservice.job.controller;

import com.microservice.job.DTO.JobDTO;
import com.microservice.job.model.Job;
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

    @GetMapping("/")
    public ResponseEntity<List<JobDTO>> getAllJobHandler(){
        List<JobDTO> allJobs = this.jobService.getAllJobs();
        return new  ResponseEntity<>(allJobs, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDTO> getJobByHandler(@PathVariable Integer id){
        JobDTO job = this.jobService.getJobById(id);
        return new  ResponseEntity<>(job, HttpStatus.ACCEPTED);
    }
}
