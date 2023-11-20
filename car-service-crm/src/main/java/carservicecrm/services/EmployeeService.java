package carservicecrm.services;

import carservicecrm.models.Employee;
import carservicecrm.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public boolean saveEmployee(Employee employee){
        try{
            employeeRepository.save(employee);
        }catch(Exception e){
            return false;
        }
       return true;
    }
}
