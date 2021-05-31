package danil.teterin.clients.access;

import org.danil.teterin.accesslevel.AccessLevelDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallBackAccessFeignClient implements AccessFeignClient{
    @Override
    public Flux<AccessLevelDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<AccessLevelDto> findById(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(Integer id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<AccessLevelDto> save(AccessLevelDto accessLevelDto) {
        return Mono.error(IllegalArgumentException::new);
    }
}
