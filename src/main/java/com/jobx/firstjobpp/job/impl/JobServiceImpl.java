package com.jobx.firstjobpp.job.impl;

import com.jobx.firstjobpp.job.dataobjects.Job;
import com.jobx.firstjobpp.job.JobSequenceGenerator;
import com.jobx.firstjobpp.job.service.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private List<Job> jobs =  new ArrayList<>();
    JobSequenceGenerator seq = JobSequenceGenerator.getInstance();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setJobSeq(seq.getNextSeq());
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long jobSeq) {
        if(!jobs.isEmpty() && jobs.size() > 0) {
            for (Job job : jobs) {
                if(job.getJobSeq().equals(jobSeq)){
                    return job;
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long jobSeq) {
        Iterator<Job> iterator = jobs.iterator();
        while (iterator.hasNext())
        {
            Job job = iterator.next();
            if(job.getJobSeq().equals(jobSeq))
            {
                iterator.remove();
                return true;
            }
        }
        return false;
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

            return true;
        }
        return false;
    }
}
