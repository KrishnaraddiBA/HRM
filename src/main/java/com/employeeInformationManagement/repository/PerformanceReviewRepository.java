package com.employeeInformationManagement.repository;
import com.employeeInformationManagement.entity.PerformanceReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceReviewRepository extends JpaRepository<PerformanceReview, Long> {
    // You can add custom query methods here if needed
}
