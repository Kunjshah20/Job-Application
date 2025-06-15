package com.jobx.firstjobpp.reviews.repositories;

import com.jobx.firstjobpp.reviews.dataobjects.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCompany_CompanySeq(Long companySeq); // JPA will handle implementation on runtime
    // At runtime , it breaks down into findBy and CompanyId and uses field company
    // The query will be like: select * from review where company_seq = ?
    // when passed companySeq, e.g: 1, company_seq will company_seq = 1
}

