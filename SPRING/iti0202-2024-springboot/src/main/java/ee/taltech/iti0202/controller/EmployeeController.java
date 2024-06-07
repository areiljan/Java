package ee.taltech.iti0202.controller;


import ee.taltech.iti0202.repository.Employee;
import ee.taltech.iti0202.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The type Employee controller.
 */
@RequiredArgsConstructor
@RestController
public class EmployeeController {
    public static final int STATUS_URL_NOT_FOUND = 404;
    public static final int STATUS_SOMETHING_WRONG_SERVERSIDE = 500;
    private final EmployeeService employeeService;

    // @GetMapping("/employees")
    // public List<Employee> getAllEmployees() {
    //     return employeeService.findAllEmployees();
    // }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Add employee response entity.
     *
     * @param employee the employee
     * @return the response entity
     */
    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        if (employeeService.isEmailAlreadyTaken(employee.getEmail())) {
            return ResponseEntity.badRequest().body("This email is already in Database.");
        } else {
            if (employeeService.addEmployee(employee)) {
                return ResponseEntity.ok("Employee added to database.");
            } else {
                return ResponseEntity.status(STATUS_SOMETHING_WRONG_SERVERSIDE).body("Something went wrong.");
            }
        }
    }

    /**
     * Delete employee response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        if (employeeService.deleteEmployeeById(id)) {
            return ResponseEntity.ok("Employee deleted");
        } else {
            return ResponseEntity.status(STATUS_URL_NOT_FOUND).body("No matching ID found in database!");
        }
    }

    /**
     * Update employee response entity.
     *
     * @param id              the id
     * @param updatedEmployee the updated employee
     * @return the response entity
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmployee) {
        String result = employeeService.updateEmployee(id, updatedEmployee);
        if ("Cannot overwrite employee data!".equals(result)) {
            return ResponseEntity.badRequest().body(result);
        } else if ("Employee not found!".equals(result)) {
            return ResponseEntity.status(STATUS_URL_NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    /**
     * Gets employees by company.
     *
     * @param company the company
     * @return the employees by company
     */
    @GetMapping("/employees")
    public List<Employee> getEmployeesByCompany(@RequestParam(name = "company", required = false) String company) {
        if (company == null || company.isEmpty() || company.isBlank()) {
            return employeeService.findAllEmployees();
        }
        return employeeService.getEmployeesByCompany(company);
    }
}
