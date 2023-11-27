package carservicecrm.services;

import carservicecrm.models.Detail;
import carservicecrm.repositories.DetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;

    public boolean saveDetail(Detail detail) {
        try{
            detailRepository.save(detail);
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
        return detailRepository.findAllInStorage();
    }

    public void updateStorageStock(Long id, Integer number){
        detailRepository.updateStorageStock(id,number);
    }
}
