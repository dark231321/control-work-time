package danil.teterin.service.impl;

import danil.teterin.mapper.CompanyMapper;
import danil.teterin.mapper.DepartmentMapper;
import danil.teterin.repo.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import danil.teterin.model.Company;
import danil.teterin.repo.CompanyRepository;
import danil.teterin.service.CompanyService;
import org.danil.teterin.company.CompanyDepartmentDto;
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
public class CompanyServiceImpl implements CompanyService {

    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final DepartmentMapper departmentMapper;

    @Override
    public Mono<Company> findById(Integer id) {
        log.info("In CompanyServiceImpl - findById {}", id);
        return companyRepository.findById(id)
                .log((Logger) log);
    }

    @Override
    public Mono<Company> save(Company company) {
        log.info("In CompanyServiceImpl - save {}", company);
        return companyRepository.save(company)
                .subscribeOn(Schedulers.elastic());
    }

    @Override
    public Flux<Company> findAll() {
        log.info("In CompanyServiceImpl - findAll");
        return companyRepository.findAll();
    }

    @Override
    public Mono<String> delete(Integer id) {
        log.info("In CompanyServiceImpl - delete by id {}", id);
        return companyRepository
                .deleteById(id)
                .map(mVoid -> departmentRepository
                        .findByCompanyId(id)
                        .map(departmentRepository::delete))
                .then(Mono.just("Deleted"));
    }

    @Override
    public Mono<String> delete(Company company) {
        log.info("In CompanyServiceImpl - delete by company {}", company);
        return companyRepository.delete(company)
                .map(mVoid -> departmentRepository
                        .findByCompanyId(company.getId())
                        .map(departmentRepository::delete))
                .then(Mono.just("Deleted"));
    }

    @Override
    public Flux<CompanyDepartmentDto> findAllCompanyDepartmentDto() {
        log.info("In CompanyServiceImpl - findAllCompanyDepartmentDto ");
        return findAll()
                .map(companyMapper::toDtoDepartment)
                .map(companyDepartmentDto -> {
                    var i = departmentRepository
                            .findByCompanyId(companyDepartmentDto.getId())
                            .map(departmentMapper::toDto)
                            .collectList();
                    companyDepartmentDto.setDepartments(i.  block());
                    return companyDepartmentDto;
                });

    }

    @Override
    public Mono<Company> findByDepartment() {
        return null;
    }

}
