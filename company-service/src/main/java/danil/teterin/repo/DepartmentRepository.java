package danil.teterin.repo;

import danil.teterin.model.Department;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DepartmentRepository extends ReactiveCrudRepository<Department, Integer> {
    Flux<Department> findByCompanyId(Integer id);
}
