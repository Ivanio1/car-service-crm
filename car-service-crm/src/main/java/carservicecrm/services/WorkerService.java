package carservicecrm.services;

import carservicecrm.models.Sto;
import carservicecrm.models.Worker;
import carservicecrm.repositories.WorkerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WorkerService {
    private final WorkerRepository workerRepository;

    public boolean saveWorker(Worker worker) {
        try{
            workerRepository.save(worker);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public List<Worker> list(){
        return workerRepository.findAll();
    }
    public Worker getWorker(Long workerId) {
        return workerRepository.findWorkerById(workerId);
    }

}
