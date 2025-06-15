package com.jobx.firstjobpp.company.impl;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.repositories.CompanyRepository;
import com.jobx.firstjobpp.company.service.CompanyService;
import com.jobx.firstjobpp.job.repositories.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);
    private CompanyRepository companyRepository;
    private JobRepository jobRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, JobRepository jobRepository) {
        this.companyRepository = companyRepository;
        this.jobRepository = jobRepository;
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

    @Override
    public void createCompany(Company company){
        if(company != null)
        {
            companyRepository.save(company);
        }else{
            return;
        }
    }

    @Override
    public boolean deleteCompanyById(Long companySeq){
        try {
            if(companyRepository.existsById(companySeq)){

                // 1. Delete all jobs related to company using company seq, custom method defined in Job Repository
                jobRepository.deleteByCompany_CompanySeq(companySeq);

                // 2. Delete company using company seq
                companyRepository.deleteById(companySeq);
                return true;
            }
            log.warn("Job with ID {} not found.", companySeq);
            return false;
        } catch (Exception e) {
            log.error("Error in deleting job: ", e);
            return false;
        }
    }
}
