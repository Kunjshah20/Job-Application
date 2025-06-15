package com.jobx.firstjobpp.reviews.controllers;

import com.jobx.firstjobpp.helper.ApiResponse;
import com.jobx.firstjobpp.reviews.dataobjects.Review;
import com.jobx.firstjobpp.reviews.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companySeq}")
public class ReviewRestController {

    private ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<ApiResponse<List<Review>>> getAllReviews(@PathVariable Long companySeq){
        List<Review> reviews = reviewService.getAllReviews(companySeq);
        if(reviews != null && !reviews.isEmpty()){
            //new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
            return new ResponseEntity<>(new ApiResponse<>("Reviews Found Successfully", reviews), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse<>("No Reviews Found.", reviews), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reviews")
    public ResponseEntity<ApiResponse<Review>> createReview(@PathVariable Long companySeq, @RequestBody Review review){
        boolean isReviewCreated = reviewService.addReview(companySeq, review);
        if(isReviewCreated){
            return new ResponseEntity<>(new ApiResponse<>("Review Added Successfully for " + review.getCompany().getCompanyName(), review), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse<>("Review not added.", null), HttpStatus.NOT_FOUND);
    }
}
