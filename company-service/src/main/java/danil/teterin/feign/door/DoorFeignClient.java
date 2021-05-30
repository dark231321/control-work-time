package danil.teterin.feign.door;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;


@ReactiveFeignClient(
        name = "control-service")
public interface DoorFeignClient {
    @GetMapping("api/control/door")
    Flux<DoorDto> findAll();
}
