package ee.taltech.iti0202.service;

import ee.taltech.iti0202.repository.Employee;
import ee.taltech.iti0202.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The type Employee service.
 */
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    /**
     * Is email already taken boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean isEmailAlreadyTaken(String email) {
        return employeeRepository.existsByEmail(email);
    }

    /**
     * Find all employees list.
     *
     * @return the list
     */
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Find employee by id optional.
     *
     * @param id the id
     * @return the optional
     */
    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Add employee boolean.
     *
     * @param employee the employee
     * @return the boolean
     */
    public boolean addEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete employee by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deleteEmployeeById(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update employee string.
     *
     * @param id              the id
     * @param updatedEmployee the updated employee
     * @return the string
     */
    public String updateEmployee(long id, Employee updatedEmployee) {
        Optional<Employee> existingEmployeeOptional = employeeRepository.findById(id);
        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();
            if (!existingEmployee.getEmail().equals(updatedEmployee.getEmail())
                    && employeeRepository.existsByEmail(updatedEmployee.getEmail())) {
                return "Cannot overwrite employee data!";
            }
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setCompany(updatedEmployee.getCompany());
            employeeRepository.save(existingEmployee);
            return "Employee data overwritten.";
        }
        return "Employee not found!";
    }

    /**
     * Gets employees by company.
     *
     * @param company the company
     * @return the employees by company
     */
    public List<Employee> getEmployeesByCompany(String company) {
        return employeeRepository.findByCompany(company);
    }
}
