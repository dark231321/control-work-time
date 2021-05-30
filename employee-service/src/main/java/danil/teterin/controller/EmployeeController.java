package danil.teterin.controller;

import danil.teterin.mapper.EmployeeMapper;
import danil.teterin.mapper.PositionMapper;
import danil.teterin.model.Employee;
import danil.teterin.service.EmployeeService;
import danil.teterin.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.danil.teterin.employee.EmployeeDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequestMapping("/api/employee/")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping("{id}")
    private Mono<EmployeeDto> findById(@PathVariable("id") String id) {
        log.debug("In EmployeeController - findById {}", id);
        return employeeService.findById(id)
                .map(employeeMapper::toDto);
    }


    @GetMapping
    private Flux<EmployeeDto> findAll() {
        log.debug("In EmployeeController - findAll ");
        return employeeService.findAll()
                .map(employeeMapper::toDto);
    }

    @PostMapping
    private Mono<EmployeeDto> save(@RequestBody EmployeeDto employeeDto) {
        log.debug("In EmployeeController - save {} ", employeeDto);
        return employeeService.save(
                employeeMapper.toEntity(employeeDto))
                .map(employeeMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody EmployeeDto employeeDto){
        log.debug("In EmployeeController - delete {} ", employeeDto);
        return employeeService.delete(
                employeeMapper.toEntity(employeeDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") String id){
        log.debug("In EmployeeController - delete by id {} ", id);
        return employeeService.delete(id);
    }

}
