package carservicecrm.repository;

import carservicecrm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    //Сделать конструктор с инициализацией соединения
    User findByEmail(String email);
}