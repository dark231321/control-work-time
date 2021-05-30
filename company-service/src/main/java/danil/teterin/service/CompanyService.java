package danil.teterin.service;

import danil.teterin.model.Company;
import org.danil.teterin.company.CompanyDepartmentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CompanyService {
    Mono<Company> findById(Integer id);
    Mono<Company> save(Company company);
    Flux<Company> findAll();
    Mono<String>  delete(Integer id);
    Mono<String>  delete(Company company);
    Flux<CompanyDepartmentDto> findAllCompanyDepartmentDto();
}
