package danil.teterin.service.impl;

import danil.teterin.model.Position;
import danil.teterin.repository.PositionRepository;
import danil.teterin.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;

import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;

    @Override
    public Mono<Position> findById(String id) {
        log.info("In PositionController - findById {}", id);
        return positionRepository.findById(id)
                .log((Logger) log)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Position> save(Position position) {
        position.setId(UUID.randomUUID().toString());
        log.info("In PositionController - save {}", position);
        return positionRepository.save(position)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Position> findAll() {
        log.info("In PositionController - findAll");
        return positionRepository.findAll()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(String id) {
        log.info("In PositionController - delete by id {}", id);
        return positionRepository
                .deleteById(id)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(Position position) {
        log.info("In EmployeeServiceImpl - delete by position {}", position);
        return positionRepository.delete(position)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
