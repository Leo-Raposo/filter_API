package com.example.filterapi.FilterAPI.services;

import com.example.filterapi.FilterAPI.models.EmployeeModel;
import com.example.filterapi.FilterAPI.repositories.EmployeeRepository;
import com.example.filterapi.FilterAPI.specifications.EmployeeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeModel> filterEmployees(String firstName, String department, Double minSalary, LocalDate hiredAfter, LocalDate hiredBefore, Boolean active) {
        return employeeRepository.findAll(EmployeeSpecification.filterEmployees(firstName, department, minSalary, hiredAfter, hiredBefore, active));
    }

    public EmployeeModel findById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public EmployeeModel saveEmployee(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }

    public EmployeeModel updateEmployee(Long id, EmployeeModel updatedEmployeeModel) {
        return employeeRepository.findById(id).map(existingEmployeeModel -> {
            existingEmployeeModel.setFirstName(updatedEmployeeModel.getFirstName());
            existingEmployeeModel.setLastName(updatedEmployeeModel.getLastName());
            existingEmployeeModel.setPosition(updatedEmployeeModel.getPosition());
            existingEmployeeModel.setSalary(updatedEmployeeModel.getSalary());
            existingEmployeeModel.setHireDate(updatedEmployeeModel.getHireDate());
            existingEmployeeModel.setDepartment(updatedEmployeeModel.getDepartment());
            existingEmployeeModel.setActive(updatedEmployeeModel.getActive());
            return employeeRepository.save(existingEmployeeModel);
        }).orElse(null);
    }

    public boolean deleteEmployee(Long id) {
        return employeeRepository.findById(id).map(employeeModel -> {
            employeeRepository.delete(employeeModel);
            return true;
        }).orElse(false);
    }
}
