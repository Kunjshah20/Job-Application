package com.jobx.firstjobpp.reviews.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobx.firstjobpp.company.dataobjects.Company;
import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long reviewSeq;

    private String title;

    private String description;

    private Double rating;

    @JsonIgnore
    @ManyToOne
    private Company company;

    public Review(){}

    public Long getReviewSeq() {
        return reviewSeq;
    }

    public void setReviewSeq(Long reviewSeq) {
        this.reviewSeq = reviewSeq;
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

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
