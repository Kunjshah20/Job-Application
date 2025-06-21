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
import java.util.Optional;

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

    @Override
    public Review getSingleReview(Long companySeq, Long reviewSeq) {
        List<Review> reviews = getAllReviews(companySeq);
        return reviews.stream()
                .filter(review -> review.getReviewSeq().equals(reviewSeq))
                .findFirst()
                .orElse(null);
    }

    public boolean updateSingleReview(Long companySeq, Long reviewSeq, Review updatedReview)
    {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewSeq);
        if(companyService.getCompanyById(companySeq) != null && reviewOptional.isPresent())
        {
            Review review = reviewOptional.get();
            review.setCompany(companyService.getCompanyById(companySeq));
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteSingleReview(Long companySeq, Long reviewSeq)
    {
        if(companyService.getCompanyById(companySeq) != null && reviewRepository.existsById(reviewSeq))
        {
            Review review = reviewRepository.findById(reviewSeq).orElse(null);
            if(review != null)
            {
                Company company = review.getCompany();
                // 1. Remove review from company
                company.getReviews().remove(review);

                // 2. Remove company reference now from review
                review.setCompany(null);

                // 3. Update company after removing the review
                companyService.updateCompany(companySeq, company);

                // 4. Now, we delete the review from Review Table
                reviewRepository.deleteById(reviewSeq);
                return true;
            }
        }
        return false;
    }
}
