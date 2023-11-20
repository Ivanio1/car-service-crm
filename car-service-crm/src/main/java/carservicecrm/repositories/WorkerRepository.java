package carservicecrm.repositories;

import carservicecrm.models.Employee;
import carservicecrm.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker,Long> {
}
