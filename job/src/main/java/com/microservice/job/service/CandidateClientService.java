package com.microservice.job.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "CANDIDATE-SERVICE")
public interface CandidateClientService {
    @PutMapping("/candidates/apply/{id}/{jobId}")
    String applyForJob(@PathVariable Integer id, @PathVariable Integer jobId);
}
