package danil.teterin.service;

import danil.teterin.model.Door;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DoorService {
    Mono<Door> findById(String id);
    Mono<Door> save(Door door);
    Flux<Door> findAll();
    Mono<String>  delete(String id);
    Mono<String>  delete(Door door);
}
