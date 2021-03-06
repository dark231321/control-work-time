package danil.teterin;


import danil.teterin.feign.door.DoorFeignClient;
import danil.teterin.feign.employee.FeignEmployeeClient;
import danil.teterin.service.CompanyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import reactivefeign.spring.config.EnableReactiveFeignClients;

import java.util.Arrays;


@EnableFeignClients
@EnableReactiveFeignClients(clients = {
        DoorFeignClient.class,
        FeignEmployeeClient.class})
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class CompanyApplication {

    public static void main(String[] args) {
        DoorFeignClient companyService = SpringApplication.run(CompanyApplication.class, args)
                .getBean(DoorFeignClient.class);
    }

}
