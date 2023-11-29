package carservicecrm.repositories;

import carservicecrm.models.Purchase;
import carservicecrm.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase,Long> {

    @Query("SELECT r FROM Purchase r")
    List<Purchase> findAllPurchases();

    @Query("SELECT q FROM Purchase q WHERE q.id = :id")
    Purchase findPurchaseById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Purchase q WHERE q.id = :purchaseId")
    void deletePurchaseById(Long purchaseId);
}