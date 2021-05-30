package danil.teterin.clients.employee;

import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "employee-service",
        path = "/api/employee/",
        fallback = FallbackFeignEmployeeClient.class)
public interface FeignEmployeeClient {

    @GetMapping
    Flux<EmployeeDto> findAll();

    @PostMapping
    Mono<EmployeeDto> save(@RequestBody EmployeeDto employeeDto);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @GetMapping("{id}")
    Mono<EmployeeDto> findById(@PathVariable("id") String id);
}
