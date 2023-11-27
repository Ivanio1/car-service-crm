package carservicecrm.services;

import carservicecrm.models.Detail;
import carservicecrm.models.DetailProvider;
import carservicecrm.models.Employee;
import carservicecrm.models.Sto;
import carservicecrm.repositories.DetailProviderRepository;
import carservicecrm.repositories.StoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class DetailProviderService {

    private final DetailProviderRepository detailProviderRepository;

    public List<DetailProvider> list() {
        return detailProviderRepository.findAll();
    }

    public boolean saveProvider(DetailProvider detailProvider) {
        try {
            detailProviderRepository.save(detailProvider);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void deleteProvider(Long id) {
        DetailProvider provider = detailProviderRepository.findById(id)
                .orElse(null);
        if (provider != null) {
            detailProviderRepository.delete(provider);
            log.info("provider with id = {} was deleted", id);
        } else {
            log.error("provider with id = {} is not found", id);
        }
    }


    public void addDetailToProvider(Long providerId, Detail detail) {
        DetailProvider detailProvider = detailProviderRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        detailProvider.addDetail(detail);
        detailProviderRepository.save(detailProvider);
    }

    public void removeDetailFromProvider(Long providerId,  Detail detail) {
        DetailProvider detailProvider = detailProviderRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        detailProvider.removeDetail(detail);
        detailProviderRepository.save(detailProvider);
    }

    public Set<Detail> getProviderDetails(Long providerId) {
        return detailProviderRepository.getProviderDetails(providerId);
    }

    public DetailProvider getProvider(Long providerId) {
        return detailProviderRepository.findProviderById(providerId);
    }
    public DetailProvider getProviderByName(String name) {
        return detailProviderRepository.findByName(name);
    }
}
