package danil.teterin.service;

import danil.teterin.model.Employee;
import danil.teterin.model.Position;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PositionService {
    Mono<Position> findById(String id);
    Mono<Position> save(Position position);
    Flux<Position> findAll();
    Mono<String>  delete(String id);
    Mono<String>  delete(Position position);
}
