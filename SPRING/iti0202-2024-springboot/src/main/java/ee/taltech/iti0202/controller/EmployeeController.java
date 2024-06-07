package ee.taltech.iti0202.controller;


import ee.taltech.iti0202.repository.Employee;
import ee.taltech.iti0202.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.findEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(employee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) {
        if (employeeService.isEmailAlreadyTaken(employee.getEmail())) {
            return ResponseEntity.badRequest().body("This email is already in Database.");
        } else {
            if (employeeService.addEmployee(employee)) {
                return ResponseEntity.ok("Employee added to database.");
            } else {
                return ResponseEntity.status(500).body("Something went wrong.");
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        if (employeeService.deleteEmployeeById(id)) {
            return ResponseEntity.ok("Employee deleted");
        } else {
            return ResponseEntity.status(404).body("No matching ID found in database!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable long id, @RequestBody Employee updatedEmployee) {
        String result = employeeService.updateEmployee(id, updatedEmployee);
        if ("Cannot overwrite employee data!".equals(result)) {
            return ResponseEntity.badRequest().body(result);
        } else if ("Employee not found!".equals(result)) {
            return ResponseEntity.status(404).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeesByCompany(@RequestParam("company") String company) {
        return employeeService.getEmployeesByCompany(company);
    }
}