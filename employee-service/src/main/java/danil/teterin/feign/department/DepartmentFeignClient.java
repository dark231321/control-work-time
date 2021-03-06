package danil.teterin.feign.department;

import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.department.DepartmentWithCompanyDto;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "company-service",
        fallback = FallbackDepartmentFeignClient.class)
public interface DepartmentFeignClient {

    @GetMapping("api/company/department/dep-comp")
    Flux<DepartmentWithCompanyDto> findDepartmentWithCompanies();

    @GetMapping("api/company/department")
    Flux<DepartmentDto> findAll();

    @PostMapping("api/company/department")
    Mono<DepartmentDto> save(@RequestBody DepartmentDto departmentDto);

    @DeleteMapping("api/company/department/{id}")
    Mono<String> delete(@PathVariable("id") int id);

    @GetMapping("api/company/department/{id}")
    Mono<DepartmentDto> findById(@PathVariable("id") int id);
}
