package danil.teterin.clients.door;

import danil.teterin.clients.department.FallbackDepartmentFeignClient;
import org.danil.teterin.door.DoorDto;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "control-service",
        path = "/api/control/door",
        fallback = FallbackDoorFeignClient.class)
public interface DoorFeignClient {

    @GetMapping("{id}")
    Mono<DoorDto> findById(@PathVariable("id") Integer id);

    @GetMapping
    Flux<DoorDto> findAll();

    @DeleteMapping
    Mono<String> delete(@RequestBody DoorDto doorDto);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") Integer id);

    @PostMapping
    Mono<DoorDto> save(@RequestBody DoorDto doorDto);
}
