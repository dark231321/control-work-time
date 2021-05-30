package danil.teterin.clients.company;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FeignClientCompanyFallBack implements FeignClientCompany {
    @Override
    public Flux<CompanyDto> findAll() {
        return Flux.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<Integer> find() {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<CompanyDto> findById(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<String> delete(int id) {
        return Mono.error(IllegalArgumentException::new);
    }

    @Override
    public Mono<CompanyDto> save(CompanyDto companyDto) {
        return Mono.error(IllegalArgumentException::new);
    }
}
