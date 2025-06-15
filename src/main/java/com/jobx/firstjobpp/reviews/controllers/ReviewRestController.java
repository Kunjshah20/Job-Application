package com.jobx.firstjobpp.reviews.controllers;

import com.jobx.firstjobpp.reviews.service.ReviewService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies/{companySeq}")
public class ReviewRestController {

    private ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
}
