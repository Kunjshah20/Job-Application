package com.jobx.firstjobpp.job.service;

import com.jobx.firstjobpp.job.dataobjects.Job;

import java.util.List;

public interface JobService {

    List<Job> findAll();

    void createJob(Job job);

    Job getJobById(Long jobSeq);

    boolean deleteJobById(Long jobSeq);

    boolean updateJob(Long jobSeq, Job job);
}
