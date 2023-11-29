package carservicecrm.repositories;

import carservicecrm.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {

    @Query(value = "SELECT u FROM Administrator u")
    List<Administrator> findAll();
}
