package danil.teterin;

import danil.teterin.clients.access.AccessFeignClient;
import danil.teterin.clients.company.FeignClientCompany;
import danil.teterin.clients.department.DepartmentFeignClient;
import danil.teterin.clients.door.DoorFeignClient;
import danil.teterin.clients.employee.FeignEmployeeClient;
import danil.teterin.clients.position.FeignPositionClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients(clients = {
        FeignClientCompany.class,
        DepartmentFeignClient.class,
        DoorFeignClient.class,
        AccessFeignClient.class,
        FeignEmployeeClient.class,
        FeignPositionClient.class
})
@EnableDiscoveryClient
@EnableFeignClients
public class FrontEndApp {
    public static void main(String[] args) {
        SpringApplication.run(FrontEndApp.class, args).getBean(DepartmentFeignClient.class);
    }
}
