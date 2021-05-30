package danil.teterin.service.impl;

import danil.teterin.feign.client.DepartmentFeignClient;
import danil.teterin.model.Control;
import danil.teterin.repository.ControlRepository;
import danil.teterin.service.ControlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.util.Logger;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class ControlServiceImpl implements ControlService {

    private final DepartmentFeignClient departmentFeignClient;

    private final ControlRepository controlRepository;

    @Override
    public Mono<String> delete(int id) {
        log.info("In CompanyServiceImpl - delete by id {}", id);
        return controlRepository
                .deleteById(id)
                .then(Mono.just("Deleted"));
    }

    @Override
    public Mono<String> delete(Control control) {
        log.info("In CompanyServiceImpl - delete by company {}", control);
        return controlRepository.delete(control)
                .then(Mono.just("Deleted"));
    }

    @Override
    public Mono<Control> findById(int id) {
        log.info("In CompanyServiceImpl - findById {}", id);
        return controlRepository.findById(id)
                .log((Logger) log);
    }

    @Override
    public Mono<Control> save(Control control) {
        log.info("In CompanyServiceImpl - save {}", control);
        return controlRepository.save(control)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<Control> findAll() {
        log.info("In CompanyServiceImpl - findAll");
        return controlRepository.findAll();
    }
}
