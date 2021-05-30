package danil.teterin.rest;

import danil.teterin.dto.department.DepartmentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import danil.teterin.dto.company.CompanyDto;
import danil.teterin.mapper.CompanyMapper;
import danil.teterin.service.CompanyService;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequestMapping("/api/company")
@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping("{id}")
    private Mono<CompanyDto> findById(@PathVariable("id") Integer id) {
        log.debug("In CompanyController - findById {}", id);
        return companyService.findById(id)
                .map(companyMapper::toDto);
    }

    @GetMapping("/int")
    private Mono<Integer> findInteger() {
        return Mono.just(1);
    }

    @GetMapping
    private Flux<CompanyDto> findAll() {
        log.debug("In CompanyController - findAll ");
        return companyService.findAll()
                .map(companyMapper::toDto);
    }

    @PostMapping
    private Mono<CompanyDto> save(@RequestBody CompanyDto companyDto) {
        log.debug("In CompanyController - save {} ", companyDto);
        return companyService.save(
                companyMapper.toEntity(companyDto))
                .map(companyMapper::toDto);
    }

    @GetMapping("/search-with-department")
    private Flux<CompanyDto> findAllWithDepartment() {
        log.debug("In CompanyController - findAllWithDepartment");
        return companyService.findAll()
                .map(companyMapper::toDto);
    }

    @DeleteMapping
    private Mono<String> delete(@RequestBody CompanyDto companyDto){
        log.debug("In CompanyController - delete {} ", companyDto);
        return companyService.delete(
                companyMapper.toEntity(companyDto));
    }

    @DeleteMapping("{id}")
    private Mono<String> delete(@PathVariable("id") int id){
        log.debug("In CompanyController - delete by id {} ", id);
        return companyService.delete(id);
    }
}
