package carservicecrm.repositories;

import carservicecrm.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail,Long> {

    Detail findDetailById(Long id);
}
