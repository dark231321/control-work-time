package danil.teterin.feign.door;

import org.danil.teterin.door.DoorDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackDoorFeignClient implements DoorFeignClient{
    @Override
    public Mono<DoorDto> findById(Integer id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Flux<DoorDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(DoorDto doorDto) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(Integer id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<DoorDto> save(DoorDto doorDto) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> deleteByDepartmentId(int id) {
        return Mono.error(IllegalArgumentException::new);
    }
}
