package danil.teterin.feign.client;

import danil.teterin.feign.dto.DepartmentDto;
import reactivefeign.FallbackFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackDepartmentFeignClient implements DepartmentFeignClient {

    @Override
    public Flux<DepartmentDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }
}
