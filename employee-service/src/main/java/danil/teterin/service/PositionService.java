package danil.teterin.service;

import danil.teterin.model.Employee;
import danil.teterin.model.Position;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PositionService {
    Mono<Position> findById(Integer id);
    Mono<Position> save(Position position);
    Flux<Position> findAll();
    Mono<String>  delete(Integer id);
    Mono<String>  delete(Position position);
}
