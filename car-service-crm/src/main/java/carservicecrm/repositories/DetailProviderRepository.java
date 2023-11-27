package carservicecrm.repositories;

import carservicecrm.models.Detail;
import carservicecrm.models.DetailProvider;
import carservicecrm.models.Employee;
import carservicecrm.models.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DetailProviderRepository extends JpaRepository<DetailProvider, Long> {

    @Query("SELECT u.details FROM DetailProvider u WHERE u.id = :providerId")
    Set<Detail> getProviderDetails(@Param("providerId") Long providerId);

    DetailProvider findProviderById(Long id);

    DetailProvider findByName(String name);

}
