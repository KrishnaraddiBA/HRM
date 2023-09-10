package com.employeeInformationManagement.service;

import com.employeeInformationManagement.entity.Employee;
import com.employeeInformationManagement.entity.PerformanceReview;
import com.employeeInformationManagement.payload.PerformanceReviewDTO;
import com.employeeInformationManagement.repository.EmployeeRepository;
import com.employeeInformationManagement.repository.PerformanceReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PerformanceReviewServiceIMPL implements PerformanceReviewService {

    private  PerformanceReviewRepository performanceReviewRepository;
    private  EmployeeRepository employeeRepository;

    @Autowired
    public PerformanceReviewServiceIMPL(
            PerformanceReviewRepository performanceReviewRepository,
            EmployeeRepository employeeRepository
    ) {
        this.performanceReviewRepository = performanceReviewRepository;
        this.employeeRepository = employeeRepository;
    }

    public PerformanceReviewDTO createPerformanceReview(PerformanceReviewDTO performanceReviewDTO) {
        // Perform any necessary business logic or validation before saving the performance review
        // For example, you can check if the associated employee exists.

        // Convert the PerformanceReviewDTO to a PerformanceReview entity
        PerformanceReview performanceReview = convertToEntity(performanceReviewDTO);

        // Save the performance review
        performanceReview = performanceReviewRepository.save(performanceReview);

        // Convert the saved PerformanceReview entity back to a PerformanceReviewDTO
        return convertToDTO(performanceReview);
    }

    public List<PerformanceReviewDTO> getAllPerformanceReviews() {
        List<PerformanceReview> performanceReviews = performanceReviewRepository.findAll();
        // Convert the list of PerformanceReview entities to a list of PerformanceReviewDTOs
        return performanceReviews.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<PerformanceReviewDTO> getPerformanceReviewById(Long id) {
        Optional<PerformanceReview> performanceReview = performanceReviewRepository.findById(id);
        // Convert the optional PerformanceReview entity to an optional PerformanceReviewDTO
        return performanceReview.map(this::convertToDTO);
    }

    public PerformanceReviewDTO updatePerformanceReview(Long id, PerformanceReviewDTO updatedReviewDTO) {
        // Perform any necessary business logic or validation before updating the performance review
        // For example, you can check if the performance review exists.

        // Convert the PerformanceReviewDTO to a PerformanceReview entity
        PerformanceReview updatedReview = convertToEntity(updatedReviewDTO);

        // Update the performance review
        updatedReview.setId(id);
        updatedReview = performanceReviewRepository.save(updatedReview);

        // Convert the updated PerformanceReview entity back to a PerformanceReviewDTO
        return convertToDTO(updatedReview);
    }

    public void deletePerformanceReview(Long id) {
        // Perform any necessary business logic or validation before deleting the performance review
        // For example, you can check if the performance review exists.

        // Delete the performance review
        performanceReviewRepository.deleteById(id);
    }

    // Helper method to convert a PerformanceReview entity to a PerformanceReviewDTO
    private PerformanceReviewDTO convertToDTO(PerformanceReview performanceReview) {
        PerformanceReviewDTO performanceReviewDTO = new PerformanceReviewDTO();
        performanceReviewDTO.setId(performanceReview.getId());
        performanceReviewDTO.setEmployeeId(performanceReview.getEmployee().getId());
        performanceReviewDTO.setReviewDate(performanceReview.getReviewDate());
        performanceReviewDTO.setFeedback(performanceReview.getFeedback());
        return performanceReviewDTO;
    }

    // Helper method to convert a PerformanceReviewDTO to a PerformanceReview entity
    private PerformanceReview convertToEntity(PerformanceReviewDTO performanceReviewDTO) {
        PerformanceReview performanceReview = new PerformanceReview();
        performanceReview.setId(performanceReviewDTO.getId());

        // Retrieve the associated employee by ID and set it in the entity
        Long employeeId = performanceReviewDTO.getEmployeeId();
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        employee.ifPresent(performanceReview::setEmployee);

        performanceReview.setReviewDate(performanceReviewDTO.getReviewDate());
        performanceReview.setFeedback(performanceReviewDTO.getFeedback());
        return performanceReview;
    }
}
