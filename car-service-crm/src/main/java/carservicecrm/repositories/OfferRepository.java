package carservicecrm.repositories;

import carservicecrm.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {


    List<Offer> findByName(String name);

//    @Query
//            ...
}
