package danil.teterin.service;

import danil.teterin.feign.dto.DepartmentDto;
import danil.teterin.model.Control;
import danil.teterin.model.Door;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ControlService {
    Mono<Control> findById(String id);
    Mono<Control> save(Control control);
    Flux<Control> findAll();
    Mono<String>  delete(String id);
    Mono<String>  delete(Control control);
}
