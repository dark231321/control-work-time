package danil.teterin;

import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.clients.department.DepartmentFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients(clients = {
        FeignClientCompany.class,
        DepartmentFeignClient.class
})
@EnableDiscoveryClient
@EnableFeignClients
public class FrontEndApp {
    public static void main(String[] args) {
        DepartmentFeignClient feignClientCompany = SpringApplication.run(FrontEndApp.class, args).getBean(DepartmentFeignClient.class);
        feignClientCompany.findAll().count().subscribe(System.out::println);
    }
}
