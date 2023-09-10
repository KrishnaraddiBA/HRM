package com.employeeInformationManagement.service;

import com.employeeInformationManagement.payload.PerformanceReviewDTO;

import java.util.List;
import java.util.Optional;

public interface  PerformanceReviewService {

    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO);

    public List<PerformanceReviewDTO> getAllPerformanceReviews();


    public Optional<PerformanceReviewDTO> getPerformanceReviewById(Long id);
    public PerformanceReviewDTO updatePerformanceReview(Long id, PerformanceReviewDTO updatedReviewDTO);

    public void deletePerformanceReview(Long id);
}
