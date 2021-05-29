package danil.teterin.rest;

import danil.teterin.dto.AccessLevelDto;
import danil.teterin.dto.ControlDto;
import danil.teterin.mapper.AccessLevelMapper;
import danil.teterin.mapper.ControlMapper;
import danil.teterin.service.AccessLevelService;
import danil.teterin.service.ControlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping("/api/control")
@RestController
@RequiredArgsConstructor
public class ControlController {

    private final ControlService controlService;
    private final ControlMapper controlMapper;

    @GetMapping("{id}")
    private Mono<ControlDto> findById(@PathVariable("id") String id) {
        log.debug("In ControlController - findById {}", id);
        return controlService.findById(id)
                .map(controlMapper::toDto);
    }


    @GetMapping
    private Flux<ControlDto> findAll() {
        log.debug("In ControlController - findAll ");
        return controlService.findAll()
                .map(controlMapper::toDto);
    }

    @PostMapping
    private Mono<ControlDto> save(@RequestBody ControlDto controlDto) {
        log.debug("In ControlController - save {} ", controlDto);
        return controlService.save(
                controlMapper.toEntity(controlDto))
                .map(controlMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody ControlDto controlDto){
        log.debug("In ControlController - delete {} ", controlDto);
        return controlService.delete(
                controlMapper.toEntity(controlDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") String id){
        log.debug("In ControlController - delete by id {} ", id);
        return controlService.delete(id);
    }
}
