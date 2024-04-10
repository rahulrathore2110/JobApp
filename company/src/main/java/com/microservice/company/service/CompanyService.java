package com.microservice.company.service;

import com.microservice.company.DTO.CompanyDTO;
import com.microservice.company.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyService {

    String createCompany(Company company);
    List<Company> getAllCompany();

    CompanyDTO getCompanyById(Integer id);
}
