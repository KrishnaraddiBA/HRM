package com.employeeInformationManagement.controller;

import com.employeeInformationManagement.payload.PerformanceReviewDTO;
import com.employeeInformationManagement.service.PerformanceReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/performancereviews")
public class PerformanceReviewController {

    private final PerformanceReviewService performanceReviewService;

    @Autowired
    public PerformanceReviewController(PerformanceReviewService performanceReviewService) {
        this.performanceReviewService = performanceReviewService;
    }

    @PostMapping
    public ResponseEntity<PerformanceReviewDTO> createPerformanceReview(@RequestBody PerformanceReviewDTO performanceReviewDTO) {
        try {
            PerformanceReviewDTO createdReview = performanceReviewService.createPerformanceReview(performanceReviewDTO);
            return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PerformanceReviewDTO>> getAllPerformanceReviews() {
        List<PerformanceReviewDTO> performanceReviews = performanceReviewService.getAllPerformanceReviews();
        return new ResponseEntity<>(performanceReviews, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceReviewDTO> getPerformanceReviewById(@PathVariable Long id) {
        Optional<PerformanceReviewDTO> performanceReview = performanceReviewService.getPerformanceReviewById(id);
        return performanceReview.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceReviewDTO> updatePerformanceReview(@PathVariable Long id, @RequestBody PerformanceReviewDTO updatedReviewDTO) {
        try {
            PerformanceReviewDTO updatedReview = performanceReviewService.updatePerformanceReview(id, updatedReviewDTO);
            return new ResponseEntity<>(updatedReview, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceReview(@PathVariable Long id) {
        try {
            performanceReviewService.deletePerformanceReview(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
