package danil.teterin.service;

import danil.teterin.model.Control;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ControlService {
    Mono<Control> findById(int id);
    Mono<Control> save(Control control);
    Flux<Control> findAll();
    Mono<String>  delete(int id);
    Mono<String>  delete(Control control);
}
