package danil.teterin.clients.department;

import org.danil.teterin.department.DepartmentDto;
import org.danil.teterin.department.DepartmentWithCompanyDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackDepartmentFeignClient implements DepartmentFeignClient {

    @Override
    public Flux<DepartmentWithCompanyDto> findDepartmentWithCompanies() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Flux<DepartmentDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<DepartmentDto> findById(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<DepartmentDto> save(DepartmentDto departmentDto) {
        return Mono.error(IllegalArgumentException::new);
    }
}
