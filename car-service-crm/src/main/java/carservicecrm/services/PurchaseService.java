package carservicecrm.services;

import carservicecrm.models.Purchase;
import carservicecrm.models.Review;
import carservicecrm.repositories.PurchaseRepository;
import carservicecrm.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    public List<Purchase> list() {
        return purchaseRepository.findAllPurchases();
    }

    public boolean savePurchase(Purchase purchase) {
        try{
            purchaseRepository.save(purchase);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public Purchase getPurchase(Long id){
        return purchaseRepository.findPurchaseById(id);
    }

    public void deletePurchase(Long id) {
        Purchase purchase = purchaseRepository.findPurchaseById(id);
        if (purchase != null) {
            purchaseRepository.deletePurchaseById(purchase.getId());
            log.info("Purchase with id = {} was deleted", id);
        } else {
            log.info("Purchase with id = {} is not found", id);
        }
    }
}
