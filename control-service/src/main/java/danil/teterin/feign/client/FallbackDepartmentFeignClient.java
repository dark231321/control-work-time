package danil.teterin.feign.client;

import danil.teterin.feign.DepartmentDto;
import reactor.core.publisher.Flux;

public class FallbackDepartmentFeignClient implements DepartmentFeignClient {

    @Override
    public Flux<DepartmentDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }
}
