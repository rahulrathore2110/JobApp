package com.microservice.job.service;

import com.microservice.job.DTO.Company;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="COMPANY-SERVICE")
public interface CompanyClientService {

    @GetMapping("/companies/{companyId}")
    Company getCompany(@PathVariable Integer companyId);

}
