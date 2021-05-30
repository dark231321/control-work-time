package danil.teterin.clients.door;

import liquibase.pro.packaged.S;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoorDto {
    private String id;
    private String name;
}
