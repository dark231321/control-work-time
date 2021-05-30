package danil.teterin.service;

import danil.teterin.model.AccessLevel;
import danil.teterin.model.Door;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccessLevelService {
    Mono<AccessLevel> findById(int id);
    Mono<AccessLevel> save(AccessLevel accessLevel);
    Flux<AccessLevel> findAll();
    Mono<String>  delete(int id);
    Mono<String>  delete(AccessLevel accessLevel);
}
