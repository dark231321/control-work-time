package danil.teterin.controller;


import danil.teterin.mapper.PositionMapper;
import danil.teterin.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.danil.teterin.position.PositionDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/employee/position")
@RestController
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;
    private final PositionMapper positionMapper;

    @GetMapping("{id}")
    private Mono<PositionDto> findById(@PathVariable("id") Integer id) {
        log.debug("In PositionController - findById {}", id);
        return positionService.findById(id)
                .map(positionMapper::toDto);
    }


    @GetMapping
    private Flux<PositionDto> findAll() {
        log.debug("In PositionController - findAll ");
        return positionService.findAll()
                .map(positionMapper::toDto);
    }

    @PostMapping
    private Mono<PositionDto> save(@RequestBody PositionDto positionDto) {
        log.debug("In PositionController - save {} ", positionDto);
        return positionService.save(
                positionMapper.toEntity(positionDto))
                .map(positionMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody PositionDto positionDto){
        log.debug("In PositionController - delete {} ", positionDto);
        return positionService.delete(
                positionMapper.toEntity(positionDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") Integer id){
        log.debug("In PositionController - delete by id {} ", id);
        return positionService.delete(id);
    }
}
