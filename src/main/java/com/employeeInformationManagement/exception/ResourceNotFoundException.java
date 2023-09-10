package com.employeeInformationManagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFoundException  extends RuntimeException{

    private String message;

    public ResourceNotFoundException(String message) {
        super(String.format("%s not found with ur fetching details",message));
        this.message = message;
    }

    public ResourceNotFoundException() {
    }
}
