package danil.teterin.clients.position;

import org.danil.teterin.position.PositionDto;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "employee-service",
        path = "/api/employee/position/",
        fallback = FallbackFeignPositionClient.class)
public interface FeignPositionClient {

    @GetMapping
    Flux<PositionDto> findAll();

    @PostMapping
    Mono<PositionDto> save(@RequestBody PositionDto positionDto);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") Integer id);

    @GetMapping("{id}")
    Mono<PositionDto> findById(@PathVariable("id") Integer id);
}
