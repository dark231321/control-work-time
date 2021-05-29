package danil.teterin.repository;

import danil.teterin.model.AccessLevel;
import danil.teterin.model.Control;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends ReactiveCrudRepository<Control, String> {
}
