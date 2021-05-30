package danil.teterin.service;

import danil.teterin.model.Department;
import org.danil.teterin.department.DepartmentWithCompanyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DepartmentService {
    Mono<Department> findById(Integer id);
    Mono<Department> save(Department department);
    Flux<Department> findAll();
    Mono<String>  delete(Integer id);
    Mono<String>  delete(Department department);
    Flux<DepartmentWithCompanyDto> findDepartmentWithCompanies();
}
