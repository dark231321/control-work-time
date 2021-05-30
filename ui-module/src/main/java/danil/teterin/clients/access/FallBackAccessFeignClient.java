package danil.teterin.clients.access;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallBackAccessFeignClient implements AccessFeignClient{
    @Override
    public Flux<AccessDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<AccessDto> findById(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(String id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<AccessDto> save(AccessDto companyDto) {
        return Mono.error(IllegalArgumentException::new);
    }
}
