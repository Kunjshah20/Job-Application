package com.jobx.firstjobpp.company.service;

import com.jobx.firstjobpp.company.dataobjects.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies();

    boolean updateCompany(Long companySeq, Company updatedCompany);

    void createCompany(Company company);

    boolean deleteCompanyById(Long companySeq);

    Company getCompanyById(Long companySeq);
}
