package carservicecrm.repositories;

import carservicecrm.models.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail,Long> {

    Detail findDetailById(Long id);

    Detail findByName(String name);

    @Query("SELECT q FROM Detail q WHERE q.storagestock > 0")
    List<Detail> findAllInStorage();

    @Transactional
    @Query(value = "SELECT * FROM is_stock_of_detail_greater(:id,:num)", nativeQuery = true)
    List<Detail> isBiggerThanNum(@Param("id") Integer id,@Param("num") Integer num);

    @Transactional
    @Query(value = "SELECT fill_detail_count(:id,:num)", nativeQuery = true)
    void updateStorageStock(@Param("id") Long id,@Param("num") Integer num);
}
