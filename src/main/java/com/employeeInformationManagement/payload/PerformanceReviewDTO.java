package com.employeeInformationManagement.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceReviewDTO {
    private Long id;
    private Long employeeId; // Use employeeId instead of EmployeeDTO for simplicity
    private Date reviewDate;
    private String feedback;

    public PerformanceReviewDTO(Long id, Long employeeId, Date reviewDate, String feedback) {
        this.id = id;
        this.employeeId = employeeId;
        this.reviewDate = reviewDate;
        this.feedback = feedback;
    }

    public PerformanceReviewDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }


// Getters and setters

    // Constructors
}

