package danil.teterin.feign.door;

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
    Mono<DoorDto> findById(@PathVariable("id") String id);

    @GetMapping
    Flux<DoorDto> findAll();

    @DeleteMapping
    Mono<String> delete(@RequestBody DoorDto doorDto);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @PostMapping
    Mono<DoorDto> save(@RequestBody DoorDto doorDto);

    @DeleteMapping("{id}")
    Mono<String> deleteByDepartmentId(@PathVariable("id") int id);
}
