package carservicecrm.repositories;

import carservicecrm.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    @Query("SELECT r FROM Review r")
    List<Review> findAllReviews();
}
