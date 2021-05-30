package danil.teterin.service.impl;

import danil.teterin.feign.door.DoorFeignClient;
import danil.teterin.feign.employee.FeignEmployeeClient;
import danil.teterin.mapper.CompanyMapper;
import danil.teterin.mapper.DepartmentMapper;
import danil.teterin.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import danil.teterin.model.Department;
import danil.teterin.repo.DepartmentRepository;
import danil.teterin.service.DepartmentService;
import org.danil.teterin.department.DepartmentWithCompanyDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.Logger;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DoorFeignClient doorFeignClient;
    private final FeignEmployeeClient feignEmployeeClient;
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @Override
    public Mono<Department> findById(Integer id) {
        log.info("In CompanyServiceImpl - findById {}", id);
        return departmentRepository.findById(id)
                .log((Logger) log);
    }

    @Override
    public Mono<Department> save(Department department) {
        log.info("In CompanyServiceImpl - save {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Flux<Department> findAll() {
        log.info("In CompanyServiceImpl - findAll");
        return departmentRepository.findAll();
    }

    @Override
    public Mono<String> delete(Integer id) {
        log.info("In CompanyServiceImpl - delete by company {}", id);
        return Mono.just(id).map(dep -> {
            departmentRepository.deleteById(id);
            this.deleteCascade(id);
            return Mono.empty();
        }).then(Mono.just("Deleted"));
    }

    @Override
    public Mono<String> delete(Department department) {
        log.info("In CompanyServiceImpl - delete by company {}", department);
        return Mono.just(department).map(dep -> {
            departmentRepository.delete(dep);
            this.deleteCascade(department.getId());
            return Mono.empty();
        }).then(Mono.just("Deleted"));
    }

    @Override
    public Flux<DepartmentWithCompanyDto> findDepartmentWithCompanies() {
        log.info("In CompanyServiceImpl - findDepartmentWithCompanies");
        return departmentRepository.findAll()
                .map(department -> {
                    var dep = departmentMapper.toDtoComp(department);
                    companyService.findById(department.getCompanyId())
                            .map(companyMapper::toDto)
                            .subscribe(dep::setCompanyDto);
                    return dep;
                });
    }

    public Mono<Object> deleteCascade(int id){
        return Mono.just(id).map(
                dep ->{
                    feignEmployeeClient.deleteByDepartmentId(id);
                    doorFeignClient.deleteByDepartmentId(id);
                    return Mono.empty();
                }
        );
    }
}
