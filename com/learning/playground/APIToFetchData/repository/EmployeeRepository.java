package com.learning.playground.apiToFetchData.repository;

import com.learning.playground.model.employee_demographics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<employee_demographics, Long> {
}
