package carservicecrm.repositories;

import carservicecrm.models.Car;
import carservicecrm.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
    Car findCarById(Long carid);
}
