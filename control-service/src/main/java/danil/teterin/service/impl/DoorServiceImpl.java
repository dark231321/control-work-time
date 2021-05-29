package danil.teterin.service.impl;

import danil.teterin.model.Door;
import danil.teterin.repository.DoorRepository;
import danil.teterin.service.DoorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class DoorServiceImpl implements DoorService {

    private final DoorRepository doorRepository;

    @Override
    public Mono<Door> findById(String id) {
        log.info("In DoorServiceImpl - findById {}", id);
        return doorRepository.findById(id)
                .log((Logger) log)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Door> save(Door door) {
        log.info("In DoorServiceImpl - save {}", door);
        return doorRepository.save(door)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Door> findAll() {
        log.info("In DoorServiceImpl - findAll");
        return doorRepository.findAll()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(String id) {
        log.info("In DoorServiceImpl - delete by id {}", id);
        return doorRepository
                .deleteById(id)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(Door door) {
        log.info("In DoorServiceImpl - delete by door {}", door);
        return doorRepository.delete(door)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
