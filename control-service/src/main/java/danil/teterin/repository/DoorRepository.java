package danil.teterin.repository;

import danil.teterin.model.Control;
import danil.teterin.model.Door;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoorRepository extends ReactiveCrudRepository<Door, String> {
}
