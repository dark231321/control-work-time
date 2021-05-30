package danil.teterin.service;

import danil.teterin.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Mono<Employee> findById(String id);
    Mono<Employee> save(Employee employee);
    Flux<Employee> findAll();
    Mono<String>  delete(String id);
    Mono<String>  delete(Employee employee);
}
