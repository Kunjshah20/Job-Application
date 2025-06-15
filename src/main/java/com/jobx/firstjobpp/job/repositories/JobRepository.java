package com.jobx.firstjobpp.job.repositories;

import com.jobx.firstjobpp.job.dataobjects.Job;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<class name, primary key data type>
public interface JobRepository extends JpaRepository<Job, Long> {

    @Transactional
    // Transactional makes sure all DB changes here happen together as one unit.
    // If something goes wrong, it rolls everything back automatically.
    // And delete by companySeq method implementation is handled by spring boot
    void deleteByCompany_CompanySeq(Long companySeq);
}
