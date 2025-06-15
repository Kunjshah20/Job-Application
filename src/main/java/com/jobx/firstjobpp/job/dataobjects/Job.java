package com.jobx.firstjobpp.job.dataobjects;

import jakarta.persistence.*;

@Entity // tell spring boot to create table in database of this class
@Table (name = "job") // tell spring boot the name of table or else it will take name of this class
public class Job {

    @Id // tell spring boot this field is primary key of table
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // tell Spring boot to auto generate Sequence
    // commented auto generate sequence as of now , as I'm using singleton class of sequence generator
    private Long jobSeq;

    private String title;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String location;

    // created default constructor as entities are objects in relational database and this is a requirement for JPA
    // JPA uses reflection for persistance of entities
    public Job(){}

    public Job(Long jobSeq, String title, String description, String minSalary, String maxSalary, String location) {
        this.jobSeq = jobSeq;
        this.title = title;
        this.description = description;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.location = location;
    }

    public Long getJobSeq() {
        return jobSeq;
    }

    public void setJobSeq(Long jobSeq) {
        this.jobSeq = jobSeq;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(String minSalary) {
        this.minSalary = minSalary;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
