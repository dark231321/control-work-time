package danil.teterin.clients.access;

import danil.teterin.clients.company.CompanyDto;
import danil.teterin.clients.company.FeignClientCompanyFallBack;
import org.springframework.web.bind.annotation.*;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(name = "control-service",
        path = "/api/control/access-level",
        fallback = FallBackAccessFeignClient.class)
public interface AccessFeignClient {
    @GetMapping("api/company/")
    Flux<AccessDto> findAll();

    @GetMapping("api/company/{id}")
    Mono<AccessDto> findById(@PathVariable("id") int id);

    @DeleteMapping("{id}")
    Mono<String> delete(@PathVariable("id") String id);

    @PostMapping
    Mono<AccessDto> save(@RequestBody AccessDto companyDto);
}
