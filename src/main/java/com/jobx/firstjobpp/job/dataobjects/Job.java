package com.jobx.firstjobpp.job.dataobjects;

public class Job {

    private Long jobSeq;

    private String title;

    private String description;

    private String minSalary;

    private String maxSalary;

    private String location;

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
