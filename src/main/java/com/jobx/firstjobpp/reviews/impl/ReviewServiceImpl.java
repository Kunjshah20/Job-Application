package com.jobx.firstjobpp.reviews.impl;

import com.jobx.firstjobpp.company.dataobjects.Company;
import com.jobx.firstjobpp.company.service.CompanyService;
import com.jobx.firstjobpp.reviews.dataobjects.Review;
import com.jobx.firstjobpp.reviews.repositories.ReviewRepository;
import com.jobx.firstjobpp.reviews.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companySeq) {
        try{
            return reviewRepository.findByCompany_CompanySeq(companySeq);
        } catch (Exception e) {
            log.warn("Error while searching for reviews: " , e);
            return null;
        }
    }

    @Override
    public boolean addReview(Long companySeq, Review review) {
        Company company = companyService.getCompanyById(companySeq);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
