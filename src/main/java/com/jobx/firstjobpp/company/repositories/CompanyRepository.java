package com.jobx.firstjobpp.company.repositories;

import com.jobx.firstjobpp.company.dataobjects.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
