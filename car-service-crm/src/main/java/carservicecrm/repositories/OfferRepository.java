package carservicecrm.repositories;

import carservicecrm.models.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {


    Offer findByName(String name);

    List<Offer> findAllByName(String name);

//    @Query
//            ...
}
