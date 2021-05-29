package danil.teterin.rest;

import danil.teterin.dto.department.DepartmentDto;
import danil.teterin.mapper.DepartmentMapper;
import danil.teterin.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

@Slf4j
@RequestMapping("/api/company/department")
@RestController
@RequiredArgsConstructor
public class DepartmentController {


    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @GetMapping("{id}")
    private Mono<DepartmentDto> findById(@PathVariable("id") Integer id) {
        log.debug("In CompanyController - findById {}", id);
        return departmentService.findById(id)
                .map(departmentMapper::toDto);
    }


    @GetMapping
    private Flux<DepartmentDto> findAll() {
        log.debug("In CompanyController - findAll ");
        return departmentService.findAll()
                .map(departmentMapper::toDto);
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") Integer id) {
        log.debug("In CompanyController - delete by id {} ", id);
        return departmentService.delete(id);
    }

    @PostMapping
    private Mono<DepartmentDto> save(@RequestBody DepartmentDto departmentDto) {
        log.debug("In CompanyController - save {} ", departmentDto);
        return departmentService.save(
                departmentMapper.toEntity(departmentDto))
                .map(departmentMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody DepartmentDto departmentDto){
        log.debug("In DepartmentController - delete {} ", departmentDto);
        return departmentService.delete(
                departmentMapper.toEntity(departmentDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") int id){
        log.debug("In DepartmentController - delete by id {} ", id);
        return departmentService.delete(id);
    }
}
