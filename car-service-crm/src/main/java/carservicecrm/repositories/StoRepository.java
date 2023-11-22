package carservicecrm.repositories;

import carservicecrm.models.Car;
import carservicecrm.models.Employee;
import carservicecrm.models.Offer;
import carservicecrm.models.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface StoRepository extends JpaRepository<Sto, Long> {

    @Query(value = "SELECT u FROM Sto u")
    List<Sto> findAll();

    @Query("SELECT u.employees FROM Sto u WHERE u.id = :stoId")
    Set<Employee> getStoEmployees(@Param("stoId") Long stoId);

    Sto findStoById(Long id);

    Sto findByName(String name);
}
