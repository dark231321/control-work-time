package danil.teterin.clients.position;

import org.danil.teterin.position.PositionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackFeignPositionClient implements FeignPositionClient {
    @Override
    public Flux<PositionDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<PositionDto> save(PositionDto positionDto) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(String id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<PositionDto> findById(String id) {
        return Mono.error(IllegalArgumentException::new);
    }
}
