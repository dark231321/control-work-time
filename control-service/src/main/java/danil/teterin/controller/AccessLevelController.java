package danil.teterin.controller;

import danil.teterin.mapper.AccessLevelMapper;
import danil.teterin.service.AccessLevelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.danil.teterin.accesslevel.AccessLevelDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/control/access-level")
@RestController
@RequiredArgsConstructor
public class AccessLevelController {

    private final AccessLevelService accessLevelService;
    private final AccessLevelMapper accessLevelMapper;

    @GetMapping("{id}")
    private Mono<AccessLevelDto> findById(@PathVariable("id") String id) {
        log.debug("In AccessLevelController - findById {}", id);
        return accessLevelService.findById(id)
                .map(accessLevelMapper::toDto);
    }


    @GetMapping
    private Flux<AccessLevelDto> findAll() {
        log.debug("In AccessLevelController - findAll ");
        return accessLevelService.findAll()
                .map(accessLevelMapper::toDto);
    }

    @PostMapping
    private Mono<AccessLevelDto> save(@RequestBody AccessLevelDto accessLevelDto) {
        log.debug("In AccessLevelController - save {} ", accessLevelDto);
        return accessLevelService.save(
                accessLevelMapper.toEntity(accessLevelDto))
                .map(accessLevelMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody AccessLevelDto accessLevelDto){
        log.debug("In AccessLevelController - delete {} ", accessLevelDto);
        return accessLevelService.delete(
                accessLevelMapper.toEntity(accessLevelDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") String id){
        log.debug("In AccessLevelController - delete by id {} ", id);
        return accessLevelService.delete(id);
    }
}
