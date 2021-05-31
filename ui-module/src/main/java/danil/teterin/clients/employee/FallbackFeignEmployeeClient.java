package danil.teterin.clients.employee;

import org.danil.teterin.employee.EmployeeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FallbackFeignEmployeeClient implements FeignEmployeeClient {
    @Override
    public Mono<String> deleteByDepartmentId(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Flux<EmployeeDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<EmployeeDto> save(EmployeeDto employeeDto) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(Integer id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<EmployeeDto> findById(Integer id) {
        return Mono.error(IllegalArgumentException::new);
    }
}
