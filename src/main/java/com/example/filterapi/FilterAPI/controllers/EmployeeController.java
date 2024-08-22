package com.example.filterapi.FilterAPI.controllers;

import com.example.filterapi.FilterAPI.models.EmployeeModel;
import com.example.filterapi.FilterAPI.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeModel>> getEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String department,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredAfter,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hiredBefore,
            @RequestParam(required = false) Boolean active) {
        List<EmployeeModel> employeeModels = employeeService.filterEmployees(firstName, department, minSalary, hiredAfter, hiredBefore, active);
        return ResponseEntity.ok(employeeModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {
        EmployeeModel employeeModel = employeeService.findById(id);
        return employeeModel != null ? ResponseEntity.ok(employeeModel) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel) {
        EmployeeModel createdEmployeeModel = employeeService.saveEmployee(employeeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployeeModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee(@PathVariable Long id, @RequestBody EmployeeModel updatedEmployeeModel) {
        EmployeeModel employeeModel = employeeService.updateEmployee(id, updatedEmployeeModel);
        return employeeModel != null ? ResponseEntity.ok(employeeModel) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        boolean deleted = employeeService.deleteEmployee(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
