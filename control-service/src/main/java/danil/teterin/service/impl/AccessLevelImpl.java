package danil.teterin.service.impl;

import danil.teterin.model.AccessLevel;
import danil.teterin.repository.AccessLevelRepository;
import danil.teterin.service.AccessLevelService;
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
public class AccessLevelImpl implements AccessLevelService {

    private final AccessLevelRepository accessLevelRepository;

    @Override
    public Mono<AccessLevel> findById(int id) {
        log.info("In AccessLevelImpl - findById {}", id);
        return accessLevelRepository.findById(id)
                .log((Logger) log)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<AccessLevel> save(AccessLevel accessLevel) {
        log.info("In AccessLevelImpl - save {}", accessLevel);
        return accessLevelRepository.save(accessLevel)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<AccessLevel> findAll() {
        log.info("In AccessLevelImpl - findAll");
        return accessLevelRepository.findAll()
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(int id) {
        log.info("In AccessLevelImpl - delete by id {}", id);
        return accessLevelRepository
                .deleteById(id)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<String> delete(AccessLevel accessLevel) {
        log.info("In AccessLevelImpl - delete by accessLevel {}", accessLevel);
        return accessLevelRepository.delete(accessLevel)
                .then(Mono.just("Deleted"))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
