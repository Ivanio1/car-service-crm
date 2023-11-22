package carservicecrm.repositories;

import carservicecrm.models.Sto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoRepository extends JpaRepository<Sto, Long> {

    @Query(value = "SELECT u FROM Sto u")
    List<Sto> findAll();
}
