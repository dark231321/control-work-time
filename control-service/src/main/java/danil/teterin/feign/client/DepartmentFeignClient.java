package danil.teterin.feign.client;

import danil.teterin.feign.dto.DepartmentDto;
import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

@ReactiveFeignClient(name = "company-service",
        fallback = FallbackDepartmentFeignClient.class)
public interface DepartmentFeignClient {

    @GetMapping("api/company/department")
    Flux<DepartmentDto> findAll();
}
