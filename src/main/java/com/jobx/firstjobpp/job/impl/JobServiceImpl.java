package com.jobx.firstjobpp.job.impl;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.repositories.CompanyRepository;
import com.jobx.firstjobpp.job.dataobjects.Job;
import com.jobx.firstjobpp.job.JobSequenceGenerator;
import com.jobx.firstjobpp.job.repositories.JobRepository;
import com.jobx.firstjobpp.job.service.JobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
    //private List<Job> jobs =  new ArrayList<>();
    JobRepository jobRepository;
    JobSequenceGenerator seq = JobSequenceGenerator.getInstance();

    CompanyRepository companyRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Job> findAll() {
        //return jobs;
        try{
            return jobRepository.findAll();
        }catch (Exception e){
            log.warn("Error while searching for jobs: ", e);
            return null;
        }
    }

    @Override
    public Job createJob(Job job) {

        if (job == null || job.getCompany() == null || job.getCompany().getCompanySeq() == null) {
            return null;
        }

        Optional<Company> companyOptional = companyRepository.findById(job.getCompany().getCompanySeq());
        if (companyOptional.isPresent()) {
            job.setJobSeq(seq.getNextSeq());
            job.setCompany(companyOptional.get());
            return jobRepository.save(job);
        }

        return null; // company not found
    }

    @Override
    public Job getJobById(Long jobSeq) {
//        if(!jobs.isEmpty() && jobs.size() > 0) {
//            for (Job job : jobs) {
//                if(job.getJobSeq().equals(jobSeq)){
//                    return job;
//                }
//            }
//        }
//        return null;
        return jobRepository.findById(jobSeq).orElse(null);
        // findById is Optional, so we used orElse
    }

    @Override
    public boolean deleteJobById(Long jobSeq) {
//        Iterator<Job> iterator = jobs.iterator();
//        while (iterator.hasNext())
//        {
//            Job job = iterator.next();
//            if(job.getJobSeq().equals(jobSeq))
//            {
//                iterator.remove();
//                return true;
//            }
//        }
//        return false;
        try {
            if(jobRepository.existsById(jobSeq)){
                jobRepository.deleteById(jobSeq);
                return true;
            }
            log.warn("Job with ID {} not found.", jobSeq);
            return false;
        } catch (Exception e) {
            log.error("Error in deleting job: ", e);
            return false;
        }
    }

    @Override
    public boolean updateJob(Long jobSeq, Job updatedJob)
    {
        Job job = getJobById(jobSeq);
        if(job != null)
        {
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll(){
        try {
            long count = jobRepository.count();
            if (count > 0) {
                jobRepository.deleteAll();
                return true;
            }

            log.warn("No jobs to delete.");
            return false;
        } catch (Exception e) {
            log.error("Error in deleting all jobs: ", e);
            return false;
        }
    }
}
