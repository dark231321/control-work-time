package danil.teterin.clients.door;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DoorDto {
    private int id;
    private String name;
}
