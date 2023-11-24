package carservicecrm.repositories;

import carservicecrm.models.Car;
import carservicecrm.models.Sto;
import carservicecrm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.active = true")
    List<User> findAllActiveUsers();

    @Query("SELECT u.cars FROM User u WHERE u.id = :userId")
    Set<Car> getUserCars(@Param("userId") Long userId);

    User findUserById(Long id);
}
