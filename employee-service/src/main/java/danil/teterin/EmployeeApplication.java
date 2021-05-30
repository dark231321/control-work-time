package danil.teterin;

import danil.teterin.feign.accesslevel.AccessFeignClient;
import danil.teterin.feign.department.DepartmentFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients(clients = {
        DepartmentFeignClient.class,
        AccessFeignClient.class
})
@EnableFeignClients
@EnableDiscoveryClient
public class EmployeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeApplication.class, args);
    }
}
