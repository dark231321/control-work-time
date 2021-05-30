package danil.teterin;

import danil.teterin.feign.client.DepartmentFeignClient;
import danil.teterin.service.ControlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@SpringBootApplication
@EnableReactiveFeignClients(clients = DepartmentFeignClient.class)
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class ControlApplication {
    public static void main(String[] args) {
        DepartmentFeignClient controlService = SpringApplication.run(ControlApplication.class, args)
                .getBean(DepartmentFeignClient.class);
        controlService.findAll().count().subscribe(System.out::println);
    }

}
