package danil.teterin.clients.employee;

import danil.teterin.clients.company.CompanyDto;
import danil.teterin.clients.department.DepartmentDto;
import danil.teterin.clients.department.FallbackDepartmentFeignClient;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "company-service",
        fallback = FallbackFeignEmployeeClient.class)
public interface FeignEmployeeClient {

    @GetMapping("api/company/department")
    Flux<EmployeeDto> findAll();

    @PostMapping("api/company/department")
    Mono<EmployeeDto> save(@RequestBody EmployeeDto employeeDto);

    @DeleteMapping("api/company/department/{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @GetMapping("api/company/department/{id}")
    Mono<EmployeeDto> findById(@PathVariable("id") String id);
}
