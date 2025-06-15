package com.jobx.firstjobpp.job.repositories;

import com.jobx.firstjobpp.job.dataobjects.Job;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<class name, primary key data type>
public interface JobRepository extends JpaRepository<Job, Long> {
}
