package com.microservice.company.controller;

import com.microservice.company.model.Company;
import com.microservice.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    ResponseEntity<String> createCompany(@RequestBody  Company company){
        String company1 = this.companyService.createCompany(company);
        return new ResponseEntity<>(company1, HttpStatus.CREATED);
    }

    @GetMapping("/")
    ResponseEntity<List<Company>> getAllCompany(){
         List<Company> companies = this.companyService.getAllCompany();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }
    @GetMapping("/{companyId}")
    ResponseEntity<Company> getCompanyById(@PathVariable Integer companyId){
        Company company = this.companyService.getCompanyById(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
