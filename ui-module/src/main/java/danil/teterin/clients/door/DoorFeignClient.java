package danil.teterin.clients.door;

import danil.teterin.clients.department.FallbackDepartmentFeignClient;
import reactivefeign.spring.config.ReactiveFeignClient;

@ReactiveFeignClient(name = "company-service",
        fallback = FallbackDoorFeignClient.class)
public interface DoorFeignClient {
}
