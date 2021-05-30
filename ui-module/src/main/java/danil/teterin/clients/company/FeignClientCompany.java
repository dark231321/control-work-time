package danil.teterin.clients.company;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "company-service",
        fallback = FeignClientCompanyFallBack.class)
public interface FeignClientCompany {
    @GetMapping("api/company/")
    Flux<CompanyDto> findAll();

    @GetMapping("api/company/{id}")
    Mono<CompanyDto> findById(@PathVariable("id") int id);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") int id);

    @PostMapping
    Mono<CompanyDto> save(@RequestBody CompanyDto companyDto);

}
