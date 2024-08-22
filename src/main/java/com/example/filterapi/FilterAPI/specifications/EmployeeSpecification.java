package com.example.filterapi.FilterAPI.specifications;

import com.example.filterapi.FilterAPI.models.EmployeeModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSpecification {

    public static Specification<EmployeeModel> filterEmployees(String firstName, String department, Double minSalary, LocalDate hiredAfter, LocalDate hiredBefore, Boolean active) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }
            if (department != null && !department.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("department"), department));
            }
            if (minSalary != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("salary"), minSalary));
            }
            if (hiredAfter != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("hireDate"), hiredAfter));
            }
            if (hiredBefore != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("hireDate"), hiredBefore));
            }
            if (active != null) {
                predicates.add(criteriaBuilder.equal(root.get("active"), active));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
