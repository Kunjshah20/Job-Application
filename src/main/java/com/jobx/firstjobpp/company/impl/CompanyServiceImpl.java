package com.jobx.firstjobpp.company.impl;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.repositories.CompanyRepository;
import com.jobx.firstjobpp.company.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        try {
            return companyRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean updateCompany(Long companySeq, Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(companySeq);
        if(companyOptional.isPresent())
        {
            Company company = companyOptional.get();
            company.setCompanyName(updatedCompany.getCompanyName());
            company.setDescription(updatedCompany.getDescription());
            company.setJobs(updatedCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }
}
