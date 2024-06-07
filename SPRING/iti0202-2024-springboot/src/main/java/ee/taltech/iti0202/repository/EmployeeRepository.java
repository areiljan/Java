package ee.taltech.iti0202.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmailIgnoreCase(String email);

    List<Employee> findAllByFirstName(String lastName);
    boolean existsByEmail(String email);
    List<Employee> findByCompany(String company);
}