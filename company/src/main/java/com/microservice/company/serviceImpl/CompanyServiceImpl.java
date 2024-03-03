package com.microservice.company.serviceImpl;

import com.microservice.company.Exception.ResourceNotFound;
import com.microservice.company.model.Company;
import com.microservice.company.repository.CompanyDao;
import com.microservice.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Override
    public String createCompany(Company company) {
        System.out.println(company.getCompanyName());
        Company company1 = this.companyDao.save(company);
        System.out.println(company1);
        return "Company Create Successfully";

    }

    @Override
    public List<Company> getAllCompany() {
        List<Company> companies = this.companyDao.findAll();
        return companies;
    }

    @Override
    public Company getCompanyById(Integer id) {
        Company company = this.companyDao.findById(id).orElseThrow(() -> new ResourceNotFound("No company find with this id" + id));
        return company;
    }
}
