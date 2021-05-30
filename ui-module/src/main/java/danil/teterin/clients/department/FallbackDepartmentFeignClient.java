package danil.teterin.clients.department;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackDepartmentFeignClient implements DepartmentFeignClient {

    @Override
    public Flux<DepartmentDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<CompanyDto> findById(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<DepartmentDto> save(DepartmentDto departmentDto) {
        return Mono.error(IllegalArgumentException::new);
    }
}
