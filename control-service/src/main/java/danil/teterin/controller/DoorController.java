package danil.teterin.controller;

import danil.teterin.dto.DoorDto;
import danil.teterin.mapper.DoorMapper;
import danil.teterin.service.DoorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/control/door")
@RestController
@RequiredArgsConstructor
public class DoorController {

    private final DoorService doorService;
    private final DoorMapper doorMapper;

    @GetMapping("{id}")
    private Mono<DoorDto> findById(@PathVariable("id") String id) {
        log.debug("In ControlController - findById {}", id);
        return doorService.findById(id)
                .map(doorMapper::toDto);
    }


    @GetMapping
    private Flux<DoorDto> findAll() {
        log.debug("In ControlController - findAll ");
        return doorService.findAll()
                .map(doorMapper::toDto);
    }

    @PostMapping
    private Mono<DoorDto> save(@RequestBody DoorDto doorDto) {
        log.debug("In ControlController - save {} ", doorDto);
        return doorService.save(
                doorMapper.toEntity(doorDto))
                .map(doorMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody DoorDto doorDto){
        log.debug("In ControlController - delete {} ", doorDto);
        return doorService.delete(
                doorMapper.toEntity(doorDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") String id){
        log.debug("In ControlController - delete by id {} ", id);
        return doorService.delete(id);
    }
}
