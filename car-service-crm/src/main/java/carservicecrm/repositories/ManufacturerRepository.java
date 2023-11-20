package carservicecrm.repositories;

import carservicecrm.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
