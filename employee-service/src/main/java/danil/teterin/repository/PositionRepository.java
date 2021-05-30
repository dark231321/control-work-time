package danil.teterin.repository;

import danil.teterin.model.Position;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PositionRepository extends ReactiveCrudRepository<Position, String> {
}
