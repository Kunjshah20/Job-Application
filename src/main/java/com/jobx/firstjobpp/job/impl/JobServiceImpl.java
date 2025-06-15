package com.jobx.firstjobpp.job.impl;

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

@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
    //private List<Job> jobs =  new ArrayList<>();
    JobRepository jobRepository;
    JobSequenceGenerator seq = JobSequenceGenerator.getInstance();

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        //return jobs;
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setJobSeq(seq.getNextSeq());
        //jobs.add(job);
        jobRepository.save(job);
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
            jobRepository.deleteById(jobSeq);
            return true;
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
