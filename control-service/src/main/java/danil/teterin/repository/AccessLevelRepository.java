package danil.teterin.repository;

import danil.teterin.model.AccessLevel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessLevelRepository extends ReactiveCrudRepository<AccessLevel, String> {
}
