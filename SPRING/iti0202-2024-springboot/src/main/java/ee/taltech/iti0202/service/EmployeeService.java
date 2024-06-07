package ee.taltech.iti0202.service;

import ee.taltech.iti0202.repository.Employee;
import ee.taltech.iti0202.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor // loob konstuktori ise parameetriga EmployeeRepository 
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public boolean isEmailAlreadyTaken(String email) {
        return employeeRepository.existsByEmail(email);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findEmployeeById(long id) {
        return employeeRepository.findById(id);
    }

    public boolean addEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteEmployeeById(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

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

    public List<Employee> getEmployeesByCompany(String company) {
        return employeeRepository.findByCompany(company);
    }
}