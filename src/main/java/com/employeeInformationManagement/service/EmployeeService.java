package com.employeeInformationManagement.service;
import com.employeeInformationManagement.entity.Department;
import com.employeeInformationManagement.entity.Employee;
import com.employeeInformationManagement.payload.DepartmentDTO;
import com.employeeInformationManagement.payload.EmployeeDTO;
import com.employeeInformationManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements EmployeeServiceImpl {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // Convert the EmployeeDTO to an Employee entity (if needed)
        Employee employee = convertToEntity(employeeDTO);

        // Perform any necessary business logic or validation before saving the employee
        // For example, you can check if the email is unique.
        // You can also validate other fields as needed.

        // Save the employee
        employee = employeeRepository.save(employee);

        // Convert the saved Employee entity back to an EmployeeDTO
        return convertToDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        // Convert the list of Employee entities to a list of EmployeeDTOs
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        // Convert the optional Employee entity to an optional EmployeeDTO
        return employee.map(this::convertToDTO);
    }

    public EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        // Convert the EmployeeDTO to an Employee entity (if needed)
        Employee updatedEmployee = convertToEntity(updatedEmployeeDTO);

        // Perform any necessary business logic or validation before updating the employee
        // For example, you can check if the employee exists and if the email is unique.

        // Update the employee
        updatedEmployee.setId(id);
        updatedEmployee = employeeRepository.save(updatedEmployee);

        // Convert the updated Employee entity back to an EmployeeDTO
        return convertToDTO(updatedEmployee);
    }

    public void deleteEmployee(Long id) {
        // Perform any necessary business logic or validation before deleting the employee
        // For example, you can check if the employee exists.

        // Delete the employee
        employeeRepository.deleteById(id);
    }

    // Helper method to convert an Employee entity to an EmployeeDTO
    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());

        // Convert the Department entity to a DepartmentDTO
        Department department = employee.getDepartment();
        if (department != null) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            employeeDTO.setDepartment(departmentDTO);
        }

        return employeeDTO;
    }

    // Helper method to convert an EmployeeDTO to an Employee entity
    private Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());

        // Convert the DepartmentDTO to a Department entity
        DepartmentDTO departmentDTO = employeeDTO.getDepartment();
        if (departmentDTO != null) {
            Department department = new Department();
            department.setId(departmentDTO.getId());
            department.setName(departmentDTO.getName());
            employee.setDepartment(department);
        }

        return employee;
    }
}
