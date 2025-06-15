package com.jobx.firstjobpp.company.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobx.firstjobpp.job.dataobjects.Job;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companySeq;

    private String companyName;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "company") // mapped by field company defined in Job Table
    private List<Job> jobs;

    // default constructor for JPA
    public Company(){}

    public Long getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Long companySeq) {
        this.companySeq = companySeq;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
