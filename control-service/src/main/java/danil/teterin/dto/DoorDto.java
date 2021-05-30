package danil.teterin.dto;

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
