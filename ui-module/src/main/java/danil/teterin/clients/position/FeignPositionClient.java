package danil.teterin.clients.position;

import danil.teterin.clients.company.CompanyDto;
import danil.teterin.clients.department.DepartmentDto;
import danil.teterin.clients.department.FallbackDepartmentFeignClient;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.ws.rs.POST;

@ReactiveFeignClient(name = "company-service",
        fallback = FallbackFeignPositionClient.class)
public interface FeignPositionClient {

    @GetMapping("api/company/department")
    Flux<PositionDto> findAll();

    @PostMapping("api/company/department")
    Mono<PositionDto> save(@RequestBody PositionDto positionDto);

    @DeleteMapping("api/company/department/{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @GetMapping("api/company/department/{id}")
    Mono<PositionDto> findById(@PathVariable("id") String id);
}
