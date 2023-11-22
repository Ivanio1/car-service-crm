package carservicecrm.services;


import carservicecrm.models.Sto;
import carservicecrm.models.User;
import carservicecrm.repositories.StoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoService {
    private final StoRepository stoRepository;

    public List<Sto> list() {
        return stoRepository.findAll();
    }

    public boolean saveSto(Sto sto) {
        try {
            stoRepository.save(sto);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteSto(Long id) {
        Sto sto = stoRepository.findById(id)
                .orElse(null);
        if (sto != null) {
            stoRepository.delete(sto);
            log.info("STO with id = {} was deleted", id);
        } else {
            log.error("STO with id = {} is not found", id);
        }
    }
}
