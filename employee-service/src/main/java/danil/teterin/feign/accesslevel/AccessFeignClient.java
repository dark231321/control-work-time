package danil.teterin.feign.accesslevel;

import org.danil.teterin.accesslevel.AccessLevelDto;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "control-service",
        path = "/api/control/access-level",
        fallback = FallBackAccessFeignClient.class)
public interface AccessFeignClient {
    @GetMapping("api/company/")
    Flux<AccessLevelDto> findAll();

    @GetMapping("api/company/{id}")
    Mono<AccessLevelDto> findById(@PathVariable("id") int id);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @PostMapping
    Mono<AccessLevelDto> save(@RequestBody AccessLevelDto companyDto);
}
