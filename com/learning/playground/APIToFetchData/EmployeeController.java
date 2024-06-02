package com.learning.playground.apiToFetchData;

import com.learning.playground.model.employee_demographics;
import com.learning.playground.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/employees")
    public List fetchEmployeeUsersData(){
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<employee_demographics> fetchEmployeeUserData(@PathVariable Long id){

        Optional<employee_demographics> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return ResponseEntity.ok(optionalEmployee.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/employee/{id}/age")
    public ResponseEntity<employee_demographics> updateEmployeeUserAge(@PathVariable Long id, @RequestBody int age){

        Optional<employee_demographics> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employee_demographics employee = optionalEmployee.get();
            employee.setAge(age);
            employeeRepository.save(employee);

            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){

        Optional<employee_demographics> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.deleteById(id);
            return ResponseEntity.ok("Record Deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<?> addEmployeeById(@RequestBody employee_demographics employee){

        Map<String, String> validateErrors = validateEmployee(employee);

        if (!validateErrors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validateErrors);
        }

        employee_demographics createdEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    public Map<String, String> validateEmployee(employee_demographics employee){

        Map<String, String> validationErrors = new HashMap<>();

        if (StringUtils.isEmpty(employee.getFirstName())) {
            validationErrors.put("FirstName", "FirstName field is required");
        }

        if (StringUtils.isEmpty(employee.getLastName())) {
            validationErrors.put("LastName", "LastName field is required");
        }

        if (StringUtils.isEmpty(employee.getGender())) {
            validationErrors.put("Gender", "Gender field is required");
        }

        if (StringUtils.isEmpty(employee.getDOB())) {
            validationErrors.put("DOB", "DOB field is required");
        }

        if ((employee.getAge()==0)) {
            validationErrors.put("Age", "Age field is required");
        }

        return validationErrors;
    }
}
