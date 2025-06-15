package com.jobx.firstjobpp.reviews.repositories;

import com.jobx.firstjobpp.reviews.dataobjects.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
