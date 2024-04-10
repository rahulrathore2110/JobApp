package com.microservice.company.serviceImpl;

import com.microservice.company.DTO.CompanyDTO;
import com.microservice.company.DTO.Review;
import com.microservice.company.Exception.ResourceNotFound;
import com.microservice.company.model.Company;
import com.microservice.company.repository.CompanyDao;
import com.microservice.company.service.CompanyService;
import com.microservice.company.service.ReviewClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ReviewClientService reviewClientService;

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
    public CompanyDTO getCompanyById(Integer id) {
        Company company = this.companyDao.findById(id).orElseThrow(() -> new ResourceNotFound("No company find with this id" + id));

        List<Review> allReviewByCompanyId = this.reviewClientService.getAllReviewByCompanyId(id);

        CompanyDTO companyDTO = this.companyToCompanyDTO(company, allReviewByCompanyId);

        return companyDTO;
    }

    public CompanyDTO companyToCompanyDTO(Company company,List<Review> review){
        CompanyDTO companyDTO= new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setCompanyName(company.getCompanyName());
        companyDTO.setDescription(company.getDescription());
        companyDTO.setReviews(review);
        return companyDTO;
    }
}
