package com.jobx.firstjobpp.company.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobx.firstjobpp.job.dataobjects.Job;
import com.jobx.firstjobpp.reviews.dataobjects.Review;
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

    @OneToMany(mappedBy = "company") // mapped by field company defined in Review Table
    private List<Review> reviews;

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

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
