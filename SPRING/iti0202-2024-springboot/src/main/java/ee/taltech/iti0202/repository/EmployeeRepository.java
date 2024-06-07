package ee.taltech.iti0202.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Employee repository.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * Find by email ignore case optional.
     *
     * @param email the email
     * @return the optional
     */
    Optional<Employee> findByEmailIgnoreCase(String email);

    /**
     * Find all by first name list.
     *
     * @param lastName the last name
     * @return the list
     */
    List<Employee> findAllByFirstName(String lastName);

    /**
     * Exists by email boolean.
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * Find by company list.
     *
     * @param company the company
     * @return the list
     */
    List<Employee> findByCompany(String company);
}
