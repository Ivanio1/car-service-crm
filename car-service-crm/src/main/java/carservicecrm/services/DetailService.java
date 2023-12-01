package carservicecrm.services;

import carservicecrm.models.Detail;
import carservicecrm.models.Tool;
import carservicecrm.repositories.DetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;

    public boolean saveDetail(Detail detail) {
        try{
            Detail existingDetail = detailRepository.findByName(detail.getName());
            if (existingDetail != null) {
                existingDetail.setStock(detail.getStoragestock()+existingDetail.getStoragestock());
                detailRepository.save(existingDetail);
            } else {
                detailRepository.save(detail);
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }


    public Detail getDetailById(Long id){
        return detailRepository.findDetailById(id);
    }

    public Detail getDetailByName(String name) {
        return detailRepository.findByName(name);
    }

    public List<Detail> list() {
        return detailRepository.findAll();
    }

    public List<Detail> listStorage() {
        List<Detail> details = detailRepository.findAllInStorage();
//        Map<String, Detail> uniqueDetails = new HashMap<>();
//        for (Detail detail : details) {
//            String name = detail.getName();
//            int storageStock = detail.getStoragestock();
//            if (uniqueDetails.containsKey(name)) {
//                Detail existingDetail = uniqueDetails.get(name);
//                existingDetail.setStoragestock(existingDetail.getStoragestock() + storageStock);
//            } else {
//                uniqueDetails.put(name, detail);
//            }
//        }
//        return new ArrayList<>(uniqueDetails.values());
        return details;
    }

    public void updateStorageStock(Long id, Integer number){
        detailRepository.updateStorageStock(id,number);
    }
}
