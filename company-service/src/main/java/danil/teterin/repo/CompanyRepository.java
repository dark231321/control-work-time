package danil.teterin.repo;

import danil.teterin.model.Company;
import danil.teterin.model.Department;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface CompanyRepository extends ReactiveCrudRepository<Company, Integer> {
}