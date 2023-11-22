package carservicecrm.repositories;

import carservicecrm.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT u FROM Employee u")
    List<Employee> findAll();

    Employee findByUserId(Long user_id);

    Employee findEmployeeById(Long id);
}
