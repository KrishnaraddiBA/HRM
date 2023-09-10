package com.employeeInformationManagement.service;

import com.employeeInformationManagement.payload.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeServiceImpl {

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
    public List<EmployeeDTO> getAllEmployees();
    public Optional<EmployeeDTO> getEmployeeById(Long id);
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO);
    public void deleteEmployee(Long id) ;
}
