package carservicecrm.repositories;

import carservicecrm.models.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WorkerRepository extends JpaRepository<Worker,Long> {

    @Query("SELECT u FROM Worker u WHERE u.id = :id")
    Worker findWorkerById(@Param("id") Long id);
}
