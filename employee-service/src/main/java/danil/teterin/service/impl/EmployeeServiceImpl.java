package danil.teterin.service.impl;

import danil.teterin.feign.accesslevel.AccessFeignClient;
import danil.teterin.feign.department.DepartmentFeignClient;
import danil.teterin.model.Employee;
import danil.teterin.repository.EmployeeRepository;
import danil.teterin.service.EmployeeService;
import danil.teterin.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    
    private final EmployeeRepository employeeRepository;

    @Override
    public Mono<Employee> findById(String id) {
        log.info("In EmployeeServiceImpl - findById {}", id);
        return employeeRepository.findById(id)
                .log((Logger) log)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Employee> save(Employee employee) {
        employee.setId(UUID.randomUUID().toString());
        log.info("In EmployeeServiceImpl - save {}", employee);
        return employeeRepository.save(employee)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Employee> findAll() {
        log.info("In EmployeeServiceImpl - findAll");
        return employeeRepository.findAll()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(String id) {
        log.info("In EmployeeServiceImpl - delete by id {}", id);
        return employeeRepository
                .deleteById(id)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(Employee employee) {
        log.info("In EmployeeServiceImpl - delete by employee {}", employee);
        return employeeRepository.delete(employee)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
