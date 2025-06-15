package com.jobx.firstjobpp.reviews.service;

import com.jobx.firstjobpp.reviews.dataobjects.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews(Long companySeq);

}
